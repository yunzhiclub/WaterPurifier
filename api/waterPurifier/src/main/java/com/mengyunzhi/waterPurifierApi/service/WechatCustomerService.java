package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chuhang on 2017/7/14.
 * 微信客户service
 */
public interface WechatCustomerService {
    //保存微信客户
    Boolean save(String openid, WechatCustomerController.CustomerInfo customerInfo);
    //获取支付参数
    WechatCustomerController.PaymentParams getPaymentParams(HttpServletRequest request) throws Exception ;
    //获取微信客户端ip
    String getClientIp(HttpServletRequest request);
    //调用统一下单接口返回的 prepay_id 参数值
    String getPackage(HttpServletRequest request) throws Exception;
    //获取统一下单参数
    WechatCustomerController.UnifiedorderParams getUnifiedorderParams(String openid) throws Exception ;
    //获取签名，签名生成算法https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3
    String getSign(WechatCustomerController.UnifiedorderParams unifiedorderParams) throws Exception ;

}
