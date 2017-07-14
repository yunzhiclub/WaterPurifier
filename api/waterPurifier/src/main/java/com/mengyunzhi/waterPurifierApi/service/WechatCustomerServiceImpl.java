package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomer;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chuhang on 2017/7/14.
 */
@Service
public class WechatCustomerServiceImpl implements WechatCustomerService {
    @Autowired
    private WechatCustomerRepository wechatCustomerRepository;
    @Autowired
    private WaterPurifierRepository waterPurifierRepository;
    @Override
    public Boolean save(String openid, WechatCustomerController.CustomerInfo customerInfo) {
        //判断是否存在客户要绑定的净水器
        WaterPurifier waterPurifier = waterPurifierRepository.findById(customerInfo.getWaterPurifierId());
        if (waterPurifier == null) {
            return Boolean.FALSE;
        }
        //保存微信客户
        WechatCustomer wechatCustomer = new WechatCustomer();
        wechatCustomer.setId(openid);
        wechatCustomer.setNickname(customerInfo.getNickName());
        wechatCustomer.setWaterPurifier(waterPurifier);
        wechatCustomerRepository.save(wechatCustomer);

        return Boolean.TRUE;
    }
}
