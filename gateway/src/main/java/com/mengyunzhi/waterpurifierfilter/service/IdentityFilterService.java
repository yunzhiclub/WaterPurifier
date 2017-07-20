package com.mengyunzhi.waterpurifierfilter.service;

/**
 * Created by chuhang on 2017/6/19.
 */
public interface IdentityFilterService {
    //sha1加密算法
    String sha1(String data);
    //验证请求的信息真伪
    Boolean isTrue(String timestamp, String randomString, String encryptionInfo);
}
