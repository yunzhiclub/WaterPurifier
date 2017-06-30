package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.ApiController.UseInfo;

/**
 * Created by chuhang on 17-6-30.
 * 用水量详情service
 */
public interface UsedWaterQuantityDetailService {
    //保存净水器使用信息
    void saveUseInfo(UseInfo useInfo);
}
