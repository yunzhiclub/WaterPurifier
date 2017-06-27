package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WaterPurifierController.WaterPurifierOutput;
import com.mengyunzhi.waterPurifierApi.repository.UsedWaterQuantityDetail;
import com.mengyunzhi.waterPurifierApi.repository.UsedWaterQuantityDetailRepository;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chuhang on 2017/6/20.
 */
@Service
public class WaterPurifierServiceImpl implements WaterPurifierService {
    private Logger logger = Logger.getLogger(WaterPurifierServiceImpl.class.getName());

    @Autowired
    private UsedWaterQuantityDetailRepository usedWaterQuantityDetailRepository;

    @Override
    public WaterPurifierOutput getRelateInfoById(Long id) {
        //实例化对象
        WaterPurifierOutput waterPurifierOutput = new WaterPurifierOutput();
        //今日用水量
        waterPurifierOutput.todayUsedWater = this.getTodayUsedWaterById(id);
        return null;
    }

    /**
     * 根据净水器编号获取今日用水量
     * @param id    净水器编号
     * @return      用水量
     */
    @Override
    public int getTodayUsedWaterById(Long id) {
        String currentDate = this.getCurrentDate();
        WaterPurifier waterPurifier = new WaterPurifier();
        usedWaterQuantityDetailRepository.findAllByWaterPurifier(waterPurifier);
        List<UsedWaterQuantityDetail> list = new ArrayList<>();
        return 0;
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

//    @Override
//    public int[] getTimestampMaxAndMixByDate(String currentDate) {
//        logger.info(currentDate);
//
//        return new int[0];
//    }


}
