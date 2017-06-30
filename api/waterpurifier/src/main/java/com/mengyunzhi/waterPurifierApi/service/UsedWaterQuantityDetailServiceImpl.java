package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.ApiController.UseInfo;
import com.mengyunzhi.waterPurifierApi.repository.UsedWaterQuantityDetail;
import com.mengyunzhi.waterPurifierApi.repository.UsedWaterQuantityDetailRepository;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chuhang on 17-6-30.
 */
@Service
public class UsedWaterQuantityDetailServiceImpl implements UsedWaterQuantityDetailService {
    @Autowired
    private UsedWaterQuantityDetailRepository usedWaterQuantityDetailRepository;
    @Autowired
    private WaterPurifierRepository waterPurifierRepository;

    @Override
    public void saveUseInfo(UseInfo useInfo) {
        //保存
        UsedWaterQuantityDetail usedWaterQuantityDetail = new UsedWaterQuantityDetail();
        usedWaterQuantityDetail.setUsedBeforeWaterQuality(useInfo.getUsedBeforeWaterQuality());
        usedWaterQuantityDetail.setUsedAfterWaterQuality(useInfo.getUsedAfterWaterQuality());
        usedWaterQuantityDetail.setUsedWaterQuantity(useInfo.getUsedWaterQuantity());
        usedWaterQuantityDetail.setLastInteractionTime(useInfo.getLastInteractTime());
        //获取关联的净水器
        WaterPurifier waterPurifier = waterPurifierRepository.findById(useInfo.getId());
        usedWaterQuantityDetail.setWaterPurifier(waterPurifier);

        usedWaterQuantityDetailRepository.save(usedWaterQuantityDetail);
        return;
    }
}
