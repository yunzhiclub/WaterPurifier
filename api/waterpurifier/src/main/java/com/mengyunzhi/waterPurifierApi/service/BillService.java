package com.mengyunzhi.waterPurifierApi.service;

/**
 * Created by chuhang on 17-6-29.
 * 订单service
 */
public interface BillService {
    //通过净水器编号获取充值信息
    Integer getRechargeInfoById(Long id);
    //根据净水器编号和订单转台处理订单
    void dealBill(Long id);
}
