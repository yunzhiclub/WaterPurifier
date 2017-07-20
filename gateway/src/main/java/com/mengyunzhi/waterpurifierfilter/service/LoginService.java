package com.mengyunzhi.waterpurifierfilter.service;

import javax.servlet.http.HttpSession;

/**
 * Created by chuhang on 2017/7/3.
 */
public interface LoginService {
    //向微信发送http请求，获取openid和session_key
    String sendHttpToWechat(String code);
    //根据openId和会话密钥生成16位的3rd_session
    String generate3RdSession(String openIdAndSessionKey);
}

