package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController;

/**
 * Created by chuhang on 2017/7/14.
 * 微信客户service
 */
public interface WechatCustomerService {
    //保存微信客户
    Boolean save(String openid, WechatCustomerController.CustomerInfo customerInfo);
}
