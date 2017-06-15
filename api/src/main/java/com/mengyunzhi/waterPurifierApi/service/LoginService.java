package com.mengyunzhi.waterPurifierApi.service;

/**
 * Created by chuhang on 2017/6/14.
 * 登录的service
 */
public interface LoginService {
    //sha1加密算法
    String sha1(String data);
    //验证请求的信息真伪
    Boolean isTrue(Long id, String timestamp, String randomString, String encryptionInfo);
}
