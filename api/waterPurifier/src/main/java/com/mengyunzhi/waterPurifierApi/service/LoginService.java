package com.mengyunzhi.waterPurifierApi.service;

import io.swagger.util.Json;

/**
 * Created by chuhang on 2017/6/28.
 * 登录service
 */
public interface LoginService {
    //向微信发送http请求，获取openid和session_key
    String sendHttpToWechat(String code);
    //根据
    String generate3RdSession(String openIdAndSessionKey);
}
