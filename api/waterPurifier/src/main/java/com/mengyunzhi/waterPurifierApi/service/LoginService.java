package com.mengyunzhi.waterPurifierApi.service;

import io.swagger.util.Json;

/**
 * Created by chuhang on 2017/6/28.
 * 登录service
 */
public interface LoginService {
    //向微信发送http请求，获取openid和session_key
    String sendHttpToWechat(String code);
    //根据openId和会话密钥生成16位的3rd_session
    String generate3RdSession(String openIdAndSessionKey);
    //sha1加密算法
    String sha1(String data);
}
