package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomer;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomerRepository;
import com.mengyunzhi.waterPurifierApi.service.WaterPurifierService;
import com.mengyunzhi.waterPurifierApi.service.WechatCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chuhang on 2017/7/6.
 * 微信客户控制器
 */
@Api(tags = "WechatCustomer 微信客户")
@RequestMapping("/WechatCustomer")
@RestController
public class WechatCustomerController {
    @Autowired
    private WechatCustomerRepository wechatCustomerRepository;
    @Autowired
    private WaterPurifierService waterPurifierService;
    @Autowired
    private WechatCustomerService wechatCustomerService;

    @ApiOperation(value = "isBind 判断是否绑定净水器", nickname = "WechatCustomer_isBind")
    @GetMapping("/isBind")
    public Object isBind(@ApiParam(value = "请求信息") HttpServletRequest request) {
        String openid = request.getHeader("openid");
        //判断是否获取到用户的openid
        if (openid == "") {
            return "error";
        }
        //判断用户是否绑定净水器
        WechatCustomer wechatCustomer = wechatCustomerRepository.findById(openid);
        if (wechatCustomer == null) {
            return Boolean.FALSE;
        }
        //根据净水器编号获取相关信息
        WaterPurifierController.WaterPurifierOutput waterPurifierOutput = waterPurifierService.getRelateInfoById(wechatCustomer.getWaterPurifier().getId());

        return waterPurifierOutput;
    }

    @ApiOperation(value = "bind 绑定净水器", nickname = "WechatCustomer_bind")
    @PostMapping("/bind")
    public Boolean bind(@ApiParam(value = "客户信息实体") @RequestBody() CustomerInfo customerInfo, HttpServletRequest request) {
        String openid = request.getHeader("openid");
        return wechatCustomerService.save(openid, customerInfo);
    }

    @ApiOperation(value = "getPaymentParams 获取支付参数", nickname = "WechatCustomer_getPaymentParams")
    @GetMapping("/getPaymentParams")
    public PaymentParams getPaymentParams(@ApiParam(value = "客户信息实体") @RequestParam("rechargeAmount") int rechargeAmount, @RequestParam("rechargeWaterQuantity") int rechargeWaterQuantity, HttpServletRequest request) {
        // 生成一条订单
        String openid = request.getHeader("openid");

        //获取支付参数
        wechatCustomerService.getPaymentParams();
        return null;
    }
    public static class PaymentParams {
        private String timeStamp;
        private String nonceStr;
        private String _package;
        private String signType;
        private String paySign;
        private String sign;

        public PaymentParams() {
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String get_package() {
            return _package;
        }

        public void set_package(String _package) {
            this._package = _package;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        @Override
        public String toString() {
            return "paymentParams{" +
                    "timeStamp='" + timeStamp + '\'' +
                    ", nonceStr='" + nonceStr + '\'' +
                    ", _package='" + _package + '\'' +
                    ", signType='" + signType + '\'' +
                    ", paySign='" + paySign + '\'' +
                    ", sign='" + sign + '\'' +
                    '}';
        }

        public PaymentParams(String timeStamp, String nonceStr, String _package, String signType, String paySign, String sign) {
            this.timeStamp = timeStamp;
            this.nonceStr = nonceStr;
            this._package = _package;
            this.signType = signType;
            this.paySign = paySign;
            this.sign = sign;
        }
    }

    public static class CustomerInfo {
        private Long waterPurifierId;
        private String nickName;

        public Long getWaterPurifierId() {
            return waterPurifierId;
        }

        public void setWaterPurifierId(Long waterPurifierId) {
            this.waterPurifierId = waterPurifierId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public CustomerInfo(Long waterPurifierId, String nickName) {
            this.waterPurifierId = waterPurifierId;
            this.nickName = nickName;
        }

        public CustomerInfo() {
        }
    }

}
