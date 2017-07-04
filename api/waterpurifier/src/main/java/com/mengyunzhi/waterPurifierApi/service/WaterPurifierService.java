package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WaterPurifierController.WaterPurifierOutput;

import java.util.Map;

/**
 * Created by chuhang on 2017/6/20.
 * 净水器service
 */
public interface WaterPurifierService {
    //根据净水器编号获取关联信息
    WaterPurifierOutput getRelateInfoById(Long id);
    //根据净水器编号获取今日用水量
    Integer getTodayUsedWaterById(Long id);
    //获取当前日期
    String getCurrentDate();
    //根据时间戳转化为当前日期
    String convertTimestampToDate(String timestamp);
    //保存，用于测试
    void save();
    //根据净水器编号获取可用水量
    Integer getLastUsedWaterById(Long id);
    //根据净水器编号获取净水前水质
    Integer getUsedBeforeWaterQualityById(Long id);
    //根据净水器编号获取净水后水质
    Integer getUsedAfterWaterQualityById(Long id);
    //根据日期和净水器编号获取今日用水量
    Integer getUsedWaterByDateAndId(String date, Long id);
    //获取一天中最小、最大时间戳
    Long[] getTimestampByDate(String date);
    //根据净水器编号获取最近7天的用水量
    Map<String, Integer> getSevenDayUsedWaterById(Long id);
    //根据日期获取最近7天的日期
    String[] getSevenDayDate();

}
