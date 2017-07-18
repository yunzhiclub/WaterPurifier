package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.constant.Constant;
import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomer;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomerRepository;
import com.mengyunzhi.waterPurifierApi.util.CommonUtil;
import com.mengyunzhi.waterPurifierApi.util.HttpUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController.UnifiedorderParams;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    public HashMap getPaymentParams(HttpServletRequest request) throws Exception{
        HashMap<String, String> paymentParams = new HashMap<>();
        //设置时间戳
        long time = System.currentTimeMillis();
        String timestamp = String.valueOf(time/1000);
        paymentParams.put("timestamp", timestamp);
        //生成随机字符串，长度为32位以下
        paymentParams.put("nonceStr", RandomStringUtils.randomAlphanumeric(30));
        // 统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=*
        paymentParams.put("_package", this.getPackage(request));
        //签名算法，暂支持 MD5
        paymentParams.put("signType", "MD5");
        //签名，格式为paySign = MD5(appId=wxd678efh567hg6787&nonceStr=5K8264ILTKCH16CQ2502SI8ZNMTM67VS&package=prepay_id=wx2017033010242291fcfe0db70013231072&signType=MD5&timeStamp=1490840662&key=qazwsxedcrfvtgbyhnujmikolp111111) = 22D9B4E54AB1950F51E0649E8810ACD6
        paymentParams.put("paySign", this.getPaySign(paymentParams));

        return paymentParams;
    }

    @Override
    public String getPackage(HttpServletRequest request) throws Exception {
        try {
            //获取统一下单参数
            UnifiedorderParams unifiedorderParams = this.getUnifiedorderParams(request);
            //转化为xml格式
            String xml = CommonUtil.UnifiedorderParamsToXML(unifiedorderParams);
            xml = xml.replace("__", "_").replace("<![CDATA[1]]>", "1");
            //请求统一下单接口
            StringBuffer buffer = HttpUtil.httpsRequest(Constant.URL_UNIFIED_ORDER, "POST", xml);
            Map<String, String> result = CommonUtil.parseXml(buffer.toString());

            //在return_code 和result_code都为SUCCESS的时候，获取prepay_id
            String return_code = result.get("return_code");
            if(StringUtils.isNotBlank(return_code) && return_code.equals("SUCCESS")) {

                String return_msg = result.get("return_msg");
                if(StringUtils.isNotBlank(return_msg) && !return_msg.equals("OK")) {
                    return "";
                }

                String prepay_Id = result.get("prepay_id");
                return "prepay_id" + "=" + prepay_Id;

            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UnifiedorderParams getUnifiedorderParams(HttpServletRequest request) throws Exception {
        UnifiedorderParams unifiedorderParams = new UnifiedorderParams();
        unifiedorderParams.setAppid(Constant.APP_ID);
        unifiedorderParams.setMch_id(Constant.MCH_ID);
        unifiedorderParams.setDevice_info("WEB");
        unifiedorderParams.setNonce_str(RandomStringUtils.randomAlphanumeric(30));
        unifiedorderParams.setSign_type("MD5");
        unifiedorderParams.setBody("JSAPI支付测试");
        unifiedorderParams.setAttach("支付测试");
        unifiedorderParams.setOut_trade_no("20122322113");
        unifiedorderParams.setTotal_fee(1);
        unifiedorderParams.setSpbill_create_ip(CommonUtil.getClientIp(request));
        unifiedorderParams.setTime_start("20091225091010");
        unifiedorderParams.setTime_expire("20091225091010");
        unifiedorderParams.setNotify_url(Constant.URL_NOTIFY);
        unifiedorderParams.setTrade_type(Constant.TRADE_TYPE);
        unifiedorderParams.setLimit_pay(Constant.LIMIT_PAY);
        unifiedorderParams.setOpenid(request.getHeader("openid"));
        unifiedorderParams.setSign(this.getSign(unifiedorderParams));

        return unifiedorderParams;
    }

    //获取签名，签名生成算法https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=4_3
    public static String getSign(UnifiedorderParams unifiedorderParams) throws Exception {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("appid=" + unifiedorderParams.getAppid())
                    .append("&attach=" + unifiedorderParams.getAttach())
                    .append("&body=" + unifiedorderParams.getBody())
                    .append("&device_info=" + unifiedorderParams.getDevice_info())
                    .append("&limit_pay=" + unifiedorderParams.getLimit_pay())
                    .append("&mch_id=" + unifiedorderParams.getMch_id())
                    .append("&nonce_str=" + unifiedorderParams.getNonce_str())
                    .append("&notify_url=" + unifiedorderParams.getNotify_url())
                    .append("&openid=" + unifiedorderParams.getOpenid())
                    .append("&out_trade_no=" + unifiedorderParams.getOut_trade_no())
                    .append("&sign_type=" + unifiedorderParams.getSign_type())
                    .append("&spbill_create_ip=" + unifiedorderParams.getSpbill_create_ip())
                    .append("&time_expire=" + unifiedorderParams.getTime_expire())
                    .append("&time_start=" + unifiedorderParams.getTime_start())
                    .append("&total_fee=" + unifiedorderParams.getTotal_fee())
                    .append("&trade_type=" + unifiedorderParams.getTrade_type())
                    .append("&key=" + Constant.APP_KEY);

            return CommonUtil.getMD5(stringBuffer.toString().trim()).toUpperCase();

        } catch (Exception e) {
            throw new Exception("获取签名出现错误");
        }

    }

    public static String getPaySign(HashMap paymentParams) throws Exception {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("appid=" + paymentParams.get("appid"))
                    .append("nonceStr=" + paymentParams.get("nonceStr"))
                    .append("package=" + paymentParams.get("package"))
                    .append("signType=" + paymentParams.get("signType"))
                    .append("timeStamp=" + paymentParams.get("timeStamp"))
                    .append("key=" + Constant.APP_KEY);

            return CommonUtil.getMD5(stringBuffer.toString().trim());

        } catch (Exception e) {
            throw new Exception("获取paySign出错");
        }

    }

}
