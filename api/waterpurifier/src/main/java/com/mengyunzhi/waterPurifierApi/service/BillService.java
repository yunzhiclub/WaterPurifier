package com.mengyunzhi.waterPurifierApi.service;

/**
 * Created by chuhang on 17-6-29.
 * 订单service
 */
public interface BillService {
    //通过净水器编号获取充值信息
    int getRechargeInfoById(Long id);
}
