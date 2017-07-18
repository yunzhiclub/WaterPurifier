package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by chuhang on 2017/7/14.
 * 微信客户service
 */
public interface WechatCustomerService {
    //保存微信客户
    Boolean save(String openid, WechatCustomerController.CustomerInfo customerInfo);
    //获取支付参数
    HashMap getPaymentParams(HttpServletRequest request) throws Exception ;
    //调用统一下单接口返回的 prepay_id ,格式如：prepay_id=*
    String getPackage(HttpServletRequest request) throws Exception;
    //获取统一下单参数
    WechatCustomerController.UnifiedorderParams getUnifiedorderParams(HttpServletRequest request) throws Exception ;

}
