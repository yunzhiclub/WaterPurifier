package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.ApiController.UseInfo;
import com.mengyunzhi.waterPurifierApi.repository.UsedWaterQuantityDetail;
import com.mengyunzhi.waterPurifierApi.repository.UsedWaterQuantityDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chuhang on 17-6-30.
 */
@Service
public class UsedWaterQuantityDetailServiceImpl implements UsedWaterQuantityDetailService {
    @Autowired
    private UsedWaterQuantityDetailRepository usedWaterQuantityDetailRepository;

    @Override
    public void saveUseInfo(UseInfo useInfo) {
        //获取上次使用信息
        UsedWaterQuantityDetail lastUseInfo = usedWaterQuantityDetailRepository.findTopByWaterPurifierIdOrderByCreateTimeDesc(useInfo.getId());
        //保存
        UsedWaterQuantityDetail usedWaterQuantityDetail = new UsedWaterQuantityDetail();
        usedWaterQuantityDetail.setId(useInfo.getId());
        usedWaterQuantityDetail.setUsedBeforeWaterQuality(useInfo.getUsedBeforeWaterQuality());
        usedWaterQuantityDetail.setUsedAfterWaterQuality(useInfo.getUsedAfterWaterQuality());
        usedWaterQuantityDetail.setUsedWaterQuantity(useInfo.getUsedWaterQuantity());
        usedWaterQuantityDetail.setThisInteractionTime(useInfo.getLastInteractTime());

        //判断上次使用信息是否为null
        if (lastUseInfo == null) {
            usedWaterQuantityDetail.setLastInteractionTime(0L);
        }
        usedWaterQuantityDetail.setLastInteractionTime(lastUseInfo.getThisInteractionTime());

        return;
    }
}
