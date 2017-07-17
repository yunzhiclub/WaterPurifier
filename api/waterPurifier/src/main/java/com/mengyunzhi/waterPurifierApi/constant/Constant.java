package com.mengyunzhi.waterPurifierApi.constant;

/**
 * Created by chuhang on 2017/7/17.
 * 常数
 */
public class Constant {

    public static final String DOMAIN = "https://api.water.mengyunzhi.com";

    public static final String APP_ID = "wxa84c8afd480f058c";

    public static final String APP_SECRET = "ec1c821f512da4895eac961eed649206";

    public static final String MCH_ID = "";  //商户号

    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public static final String URL_NOTIFY = Constant.DOMAIN + "/api/notify";

    public static final String TIME_FORMAT = "yyyyMMddHHmmss";  //订单生成时间格式

    public static final String TRADE_TYPE = "JSAPI";    //交易类型——小程序支付统一下单接口

    public static final String LIMIT_PAY = "no_credit";    //指定支付方式——指定不能使用信用卡支付
}
