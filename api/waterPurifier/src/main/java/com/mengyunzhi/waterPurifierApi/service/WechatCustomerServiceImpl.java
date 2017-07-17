package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomer;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomerRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController.PaymentParams;

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

    public PaymentParams getPaymentParams() {
        PaymentParams paymentParams = new PaymentParams();
        //设置时间戳
        long time = System.currentTimeMillis();
        String timestamp = String.valueOf(time/1000);
        paymentParams.setTimeStamp(timestamp);
        //生成随机字符串，长度为32位以下
        paymentParams.setNonceStr(RandomStringUtils.randomAlphanumeric(30));
        // TODO 统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=*

        //签名算法，暂支持 MD5
        paymentParams.setSignType("MD5");
        //签名，格式为paySign = MD5(appId=wxd678efh567hg6787&nonceStr=5K8264ILTKCH16CQ2502SI8ZNMTM67VS&package=prepay_id=wx2017033010242291fcfe0db70013231072&signType=MD5&timeStamp=1490840662&key=qazwsxedcrfvtgbyhnujmikolp111111) = 22D9B4E54AB1950F51E0649E8810ACD6
        return paymentParams;
    }
}
