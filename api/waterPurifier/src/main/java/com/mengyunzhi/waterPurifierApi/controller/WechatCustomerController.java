package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomer;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomerRepository;
import com.mengyunzhi.waterPurifierApi.service.BillService;
import com.mengyunzhi.waterPurifierApi.service.WaterPurifierService;
import com.mengyunzhi.waterPurifierApi.service.WechatCustomerService;
import io.swagger.annotations.*;
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
    @Autowired
    private BillService billService;

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
    @PostMapping("/getPaymentParams")
    public PaymentParams getPaymentParams(@ApiParam(value = "客户信息实体") @RequestParam("rechargeAmount") int rechargeAmount, @RequestParam("rechargeWaterQuantity") int rechargeWaterQuantity, HttpServletRequest request) {
        // 生成一条订单
        String openid = request.getHeader("openid");
        billService.generateBill(openid, rechargeAmount, rechargeWaterQuantity);

        //获取支付参数
        wechatCustomerService.getPaymentParams();
        return null;
    }

    @ApiModel("支付参数")
    public static class PaymentParams {
        @ApiModelProperty("时间戳")
        private String timeStamp;
        @ApiModelProperty("随机次富川")
        private String nonceStr;
        @ApiModelProperty("prepay_id 参数值")
        private String _package;
        @ApiModelProperty("signType")
        private String signType;
        @ApiModelProperty("签名")
        private String paySign;
        @ApiModelProperty("将以上数据再次签名")
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

    @ApiModel("客户信息")
    public static class CustomerInfo {
        @ApiModelProperty("净水器id")
        private Long waterPurifierId;

        @ApiModelProperty("昵称")
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

    @ApiModel("支付信息")
    public static class PayInfo {
        @ApiModelProperty("净水器id")
        private Long waterPurifierId;
        @ApiModelProperty("充值金额")
        private int rechargeAmount;
        @ApiModelProperty("充值水量")
        private int rechargeWaterQuantity;

        public Long getWaterPurifierId() {
            return waterPurifierId;
        }

        public void setWaterPurifierId(Long waterPurifierId) {
            this.waterPurifierId = waterPurifierId;
        }

        public int getRechargeAmount() {
            return rechargeAmount;
        }

        public void setRechargeAmount(int rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
        }

        public int getRechargeWaterQuantity() {
            return rechargeWaterQuantity;
        }

        public void setRechargeWaterQuantity(int rechargeWaterQuantity) {
            this.rechargeWaterQuantity = rechargeWaterQuantity;
        }

        @Override
        public String toString() {
            return "PayInfo{" +
                    "waterPurifierId=" + waterPurifierId +
                    ", rechargeAmount=" + rechargeAmount +
                    ", rechargeWaterQuantity=" + rechargeWaterQuantity +
                    '}';
        }

        public PayInfo(Long waterPurifierId, int rechargeAmount, int rechargeWaterQuantity) {
            this.waterPurifierId = waterPurifierId;
            this.rechargeAmount = rechargeAmount;
            this.rechargeWaterQuantity = rechargeWaterQuantity;
        }

        public PayInfo() {

        }
    }

}
