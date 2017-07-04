package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WaterPurifierController.WaterPurifierOutput;
import com.mengyunzhi.waterPurifierApi.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chuhang on 2017/6/20.
 * 净水器ServiceImpl
 */
@Service
public class WaterPurifierServiceImpl implements WaterPurifierService {
    private Logger logger = Logger.getLogger(WaterPurifierServiceImpl.class.getName());
    @Autowired
    private UsedWaterQuantityDetailRepository usedWaterQuantityDetailRepository;
    @Autowired
    private WaterPurifierRepository waterPurifierRepository;
    @Autowired
    private WaterPurifierService waterPurifierService;
    @Autowired
    private FilterChipRepository filterChipRepository;

    @Override
    public WaterPurifierOutput getRelateInfoById(Long id) {
        //实例化对象
        WaterPurifierOutput waterPurifierOutput = new WaterPurifierOutput();
        //净水器编号
        waterPurifierOutput.setId(id);
        //今日用水量
        waterPurifierOutput.setTodayUsedWater(waterPurifierService.getTodayUsedWaterById(id));
        //剩余用水量
        waterPurifierOutput.setLastUsedWater(this.getLastUsedWaterById(id));
        //Todo剩余滤芯（怎么算）
        waterPurifierOutput.setLastFilterChip(70);
        //净水前水质状态
        waterPurifierOutput.setUsedBeforeWaterQuality(this.getUsedBeforeWaterQualityById(id));
        //净水后水质状态
        waterPurifierOutput.setUsedAfterWaterQuality(this.getUsedAfterWaterQualityById(id));
        //最近一周用水量
        waterPurifierOutput.setRecentOneWeekUsedWater(this.getSevenDayUsedWaterById(id));

        return waterPurifierOutput;
    }

    /**
     * 根据净水器编号获取今日用水量
     * @param id    净水器编号
     * @return      用水量
     */
    @Override
    public Integer getTodayUsedWaterById(Long id) {
        //获取当前日期
        String currentDate = this.getCurrentDate();
        return this.getUsedWaterByDateAndId(currentDate, id);
    }


    /**
     * 获取当前日期
     * @return
     */
    @Override
     public String getCurrentDate(){
         //获取当前时间戳
         long time = System.currentTimeMillis();
         String timestamp = String.valueOf(time/1000);
         //根据时间戳获取当前日期
         String currentDate = this.convertTimestampToDate(timestamp);
         return currentDate;
     }

    /**
     * 将时间戳转化为日期格式
     * @param timestamp     时间戳
     * @return
     */
    @Override
     public String convertTimestampToDate(String timestamp) {
         String format = "yyyy-MM-dd";
         SimpleDateFormat date = new SimpleDateFormat(format);
         return date.format(new Date(Long.valueOf(timestamp+"000")));
     }

    @Override
    public void save() {
        long time = System.currentTimeMillis();
        Long timestamp = Long.valueOf(time/1000);
        //净水器实体
        WaterPurifier waterPurifier = new WaterPurifier();
        waterPurifier.setCreateTime(timestamp);
        waterPurifierRepository.save(waterPurifier);

        //测试用水量详情
        UsedWaterQuantityDetail usedWaterQuantityDetail = new UsedWaterQuantityDetail();
        usedWaterQuantityDetail.setUsedWaterQuantity(12);
        usedWaterQuantityDetail.setWaterPurifier(waterPurifier);
        usedWaterQuantityDetail.setUsedAfterWaterQuality(20);
        usedWaterQuantityDetail.setUsedBeforeWaterQuality(2210);
        usedWaterQuantityDetailRepository.save(usedWaterQuantityDetail);

        //测试滤芯实体，获取客用水量
        FilterChip filterChip = new FilterChip();
        filterChip.setAvailableWaterQuantity(1000);
        filterChip.setWaterPurifier(waterPurifier);
        filterChip.setCreateTime(timestamp);
        filterChipRepository.save(filterChip);


    }

    //根据净水器编号获取最后一次安装的滤芯
    @Override
    public Integer getLastUsedWaterById(Long id) {
        FilterChip filterChip = filterChipRepository.findTopByWaterPurifierIdOrderByInstallTimeDesc(id);
        if (filterChip == null) {
            return null;
        }
        return filterChip.getAvailableWaterQuantity();
    }

    //根据净水器编号获取最近一次的用水前水质
    @Override
    public Integer getUsedBeforeWaterQualityById(Long id) {
        UsedWaterQuantityDetail usedWaterQuantityDetail = usedWaterQuantityDetailRepository.findTopByWaterPurifierIdOrderByCreateTimeDesc(id);
        if (usedWaterQuantityDetail == null) {
            return null;
        }
        return usedWaterQuantityDetail.getUsedBeforeWaterQuality();
    }

    //根据净水器编号获取最近一次的用水后水质
    @Override
    public Integer getUsedAfterWaterQualityById(Long id) {
        UsedWaterQuantityDetail usedWaterQuantityDetail = usedWaterQuantityDetailRepository.findTopByWaterPurifierIdOrderByCreateTimeDesc(id);
        if (usedWaterQuantityDetail == null) {
            return null;
        }
        return usedWaterQuantityDetail.getUsedAfterWaterQuality();
    }

    //根据日期获取今日用水量
    @Override
    public Integer getUsedWaterByDateAndId(String date, Long id) {
        //获取当天最小、最大时间戳
        Long[] timestamp = this.getTimestampByDate(date);

        //获取今日用水记录
        List<UsedWaterQuantityDetail> lists = new ArrayList<>();
        lists = (List<UsedWaterQuantityDetail>)usedWaterQuantityDetailRepository.findByWaterPurifierIdAndCreateTimeBetween(id, timestamp[0], timestamp[1]);

        //将今日每次用水量累加起来
        List<Integer> todayUsedWater = new ArrayList<>();
        lists.forEach(list->{
            todayUsedWater.add(list.getUsedWaterQuantity());
        });
        return todayUsedWater.stream().mapToInt(Integer::intValue).sum();
    }

    //获取一天中最小、最大时间戳
    @Override
    public Long[] getTimestampByDate(String date) {
        // 定义数组
        Long[] timestamp = new Long[2];
        // 捕捉parse语句产生的异常，
        try {
            SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            //获取一天中最早的时间戳
            String timeEarliest = date + " 00:00:00";
            Date timeEarliestFormat = format.parse(timeEarliest);
            timestamp[0] = timeEarliestFormat.getTime()/1000;

            //获取一天中最晚的时间戳
            String timeLatest = date + " 23:59:59";
            Date timeLatestFormat = format.parse(timeLatest);
            timestamp[1] = timeLatestFormat.getTime()/1000;

        } catch (ParseException e) {              // Insert this block.
            e.printStackTrace();
        }

        return timestamp;
    }

    @Override
    public Map<String, Integer> getSevenDayUsedWaterById(Long id) {
        //定义数组，长度为7
        Map<String, Integer> maps = new TreeMap<>();
        //根据当前日期推算出最近7天的日期
        String[] sevenDayDate = this.getSevenDayDate();
        //获取最近7天每一天的用水量,并按日期排序
        for (int i = 0; i < 7; i++) {
            //赋值，（"2017-06-28": 371）形式
            maps.put(sevenDayDate[i], this.getUsedWaterByDateAndId(sevenDayDate[i], id));
        }
        return maps;
    }

    @Override
    public String[] getSevenDayDate() {
        //定义日期格式
        String format = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        //获取当前日期
        Date date=new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        //定义日期长度为7，并循环输出
        String[] dates = new String[7];
        dates[0] = dateFormat.format(now.getTime());
        for (int i = 0; i < 6; i++) {
            now.set(Calendar.DATE,now.get(Calendar.DATE) - 1);
            dates[i + 1] = dateFormat.format(now.getTime());
        }
        return dates;
    }


}
