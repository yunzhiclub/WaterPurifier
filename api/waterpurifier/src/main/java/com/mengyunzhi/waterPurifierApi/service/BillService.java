package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController;

/**
 * Created by chuhang on 17-6-29.
 * 订单service
 */
public interface BillService {
    //通过净水器编号获取充值信息
    Integer getRechargeInfoById(Long id);
    //根据净水器编号和订单转台处理订单
    void dealBill(Long id);
    //生成一条订单
    void generateBill(String openid, WechatCustomerController.PayInfo payInfo);
    //生成订单编号，订单格式为：20170717104204（日期）+ 001（同一秒内生成的第“n”条订单，此例中n=001）
    Long generateBillNumber();
    //设置订单后三位数
    Long getBillLastThree(Long time);
}
