package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WechatCustomer;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomerRepository;
import com.mengyunzhi.waterPurifierApi.service.BillService;
import com.mengyunzhi.waterPurifierApi.service.BillServiceImpl;
import com.mengyunzhi.waterPurifierApi.service.WaterPurifierService;
import com.mengyunzhi.waterPurifierApi.service.WechatCustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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
    public HashMap getPaymentParams(@ApiParam(value = "客户信息实体") @RequestBody PayInfo payInfo, HttpServletRequest request) throws Exception{
        // 生成一条订单,并获取订单id
        String openid = request.getHeader("openid");
        Long id = billService.generateBillAndGetId(openid, payInfo);
        //返回支付参数
        HashMap<String, String> paymentParams = wechatCustomerService.getPaymentParams(request);
        //设置订单签名，微信服务器通知更新状态时，用于签名校验
        billService.setSignById(id, paymentParams.get("sign"));

        return paymentParams;
    }

    @ApiModel("统一下单参数")
    public static class UnifiedorderParams{
        @ApiModelProperty("小程序ID")
        private String appid;
        @ApiModelProperty("商户号")
        private String mch_id;
        @ApiModelProperty("设备号")
        private String device_info;
        @ApiModelProperty("随机字符串")
        private String nonce_str;
        @ApiModelProperty("签名")
        private String sign;
        @ApiModelProperty("签名类型")
        private String sign_type;
        @ApiModelProperty("商品描述")
        private String body;
        @ApiModelProperty("附加数据")
        private String attach;
        @ApiModelProperty("商户订单号")
        private String out_trade_no;
        @ApiModelProperty("总金额")
        private int total_fee;
        @ApiModelProperty("终端IP")
        private String spbill_create_ip;
        @ApiModelProperty("交易起始时间")
        private String time_start;
        @ApiModelProperty("交易结束时间")
        private String time_expire;
        @ApiModelProperty("通知地址")
        private String notify_url;
        @ApiModelProperty("交易类型")
        private String trade_type;
        @ApiModelProperty("指定支付方式")
        private String limit_pay;
        @ApiModelProperty("用户标识")
        private String openid;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getDevice_info() {
            return device_info;
        }

        public void setDevice_info(String device_info) {
            this.device_info = device_info;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSign_type() {
            return sign_type;
        }

        public void setSign_type(String sign_type) {
            this.sign_type = sign_type;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getAttach() {
            return attach;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public int getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(int total_fee) {
            this.total_fee = total_fee;
        }

        public String getSpbill_create_ip() {
            return spbill_create_ip;
        }

        public void setSpbill_create_ip(String spbill_create_ip) {
            this.spbill_create_ip = spbill_create_ip;
        }

        public String getTime_start() {
            return time_start;
        }

        public void setTime_start(String time_start) {
            this.time_start = time_start;
        }

        public String getTime_expire() {
            return time_expire;
        }

        public void setTime_expire(String time_expire) {
            this.time_expire = time_expire;
        }

        public String getNotify_url() {
            return notify_url;
        }

        public void setNotify_url(String notify_url) {
            this.notify_url = notify_url;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getLimit_pay() {
            return limit_pay;
        }

        public void setLimit_pay(String limit_pay) {
            this.limit_pay = limit_pay;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public UnifiedorderParams(String appid, String mch_id, String device_info, String nonce_str, String sign, String sign_type, String body, String attach, String out_trade_no, int total_fee, String spbill_create_ip, String time_start, String time_expire, String notify_url, String trade_type, String limit_pay, String openid) {

            this.appid = appid;
            this.mch_id = mch_id;
            this.device_info = device_info;
            this.nonce_str = nonce_str;
            this.sign = sign;
            this.sign_type = sign_type;
            this.body = body;
            this.attach = attach;
            this.out_trade_no = out_trade_no;
            this.total_fee = total_fee;
            this.spbill_create_ip = spbill_create_ip;
            this.time_start = time_start;
            this.time_expire = time_expire;
            this.notify_url = notify_url;
            this.trade_type = trade_type;
            this.limit_pay = limit_pay;
            this.openid = openid;
        }

        public UnifiedorderParams() {

        }

        @Override
        public String toString() {
            return "UnifiedorderParams{" +
                    "appid='" + appid + '\'' +
                    ", mch_id='" + mch_id + '\'' +
                    ", device_info='" + device_info + '\'' +
                    ", nonce_str='" + nonce_str + '\'' +
                    ", sign='" + sign + '\'' +
                    ", sign_type='" + sign_type + '\'' +
                    ", body='" + body + '\'' +
                    ", attach='" + attach + '\'' +
                    ", out_trade_no='" + out_trade_no + '\'' +
                    ", total_fee=" + total_fee +
                    ", spbill_create_ip='" + spbill_create_ip + '\'' +
                    ", time_start='" + time_start + '\'' +
                    ", time_expire='" + time_expire + '\'' +
                    ", notify_url='" + notify_url + '\'' +
                    ", trade_type='" + trade_type + '\'' +
                    ", limit_pay='" + limit_pay + '\'' +
                    ", openid='" + openid + '\'' +
                    '}';
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
