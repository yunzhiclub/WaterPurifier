package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WaterPurifierController.WaterPurifierOutput;

/**
 * Created by chuhang on 2017/6/20.
 * 净水器service
 */
public interface WaterPurifierService {
    //根据净水器编号获取关联信息
    WaterPurifierOutput getRelateInfoById(Long id);
    //根据净水器编号获取今日用水量
    int getTodayUsedWaterById(Long id);
    //获取当前日期
    String getCurrentDate();
    //根据时间戳转化为当前日期
    String convertTimestampToDate(String timestamp);

}
