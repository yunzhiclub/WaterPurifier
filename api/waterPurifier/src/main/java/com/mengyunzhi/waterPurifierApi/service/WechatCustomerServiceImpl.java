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
import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController.PaymentParams;
import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController.UnifiedorderParams;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
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

    public PaymentParams getPaymentParams(HttpServletRequest request) throws Exception{
        PaymentParams paymentParams = new PaymentParams();
        //设置时间戳
        long time = System.currentTimeMillis();
        String timestamp = String.valueOf(time/1000);
        paymentParams.setTimeStamp(timestamp);
        //生成随机字符串，长度为32位以下
        paymentParams.setNonceStr(RandomStringUtils.randomAlphanumeric(30));
        // TODO 统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=*
        String _package = this.getPackage(request);
        paymentParams.set_package(_package);
        //签名算法，暂支持 MD5
        paymentParams.setSignType("MD5");
        //签名，格式为paySign = MD5(appId=wxd678efh567hg6787&nonceStr=5K8264ILTKCH16CQ2502SI8ZNMTM67VS&package=prepay_id=wx2017033010242291fcfe0db70013231072&signType=MD5&timeStamp=1490840662&key=qazwsxedcrfvtgbyhnujmikolp111111) = 22D9B4E54AB1950F51E0649E8810ACD6
        return paymentParams;
    }

    @Override
    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    @Override
    public String getPackage(HttpServletRequest request) throws Exception {
        try {
            UnifiedorderParams unifiedorderParams = this.getUnifiedorderParams(request.getHeader("openid"));
            String xml = CommonUtil.UnifiedorderParamsToXML(unifiedorderParams);
            xml = xml.replace("__", "_").replace("<![CDATA[1]]>", "1");
            StringBuffer buffer = HttpUtil.httpsRequest(Constant.URL_UNIFIED_ORDER, "POST", xml);
            Map<String, String> result = CommonUtil.parseXml(buffer.toString());

            String return_code = result.get("return_code");
            if(StringUtils.isNotBlank(return_code) && return_code.equals("SUCCESS")) {

                String return_msg = result.get("return_msg");
                if(StringUtils.isNotBlank(return_msg) && !return_msg.equals("OK")) {
                    return "";
                }

                String prepay_Id = result.get("prepay_id");
                return prepay_Id;

            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UnifiedorderParams getUnifiedorderParams(String openid) throws Exception {
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
        unifiedorderParams.setSpbill_create_ip("127.0.0.1");
        unifiedorderParams.setTime_start("20091225091010");
        unifiedorderParams.setTime_expire("20091225091010");
        unifiedorderParams.setNotify_url(Constant.URL_NOTIFY);
        unifiedorderParams.setTrade_type("JSAPI");
        unifiedorderParams.setLimit_pay("no_credit");
        unifiedorderParams.setOpenid(openid);
        unifiedorderParams.setSign(this.getSign(unifiedorderParams));
        return unifiedorderParams;
    }

    //对字符串进行md5加密
    @Override
    public String getSign(UnifiedorderParams unifiedorderParams) throws Exception {
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
            return this.getMD5(stringBuffer.toString().trim()).toUpperCase();
        } catch (Exception e) {
            throw new Exception("获取签名出现错误");
        }

    }

    //对字符串md5加密
    public static String getMD5(String str) throws Exception {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误");
        }
    }
}
