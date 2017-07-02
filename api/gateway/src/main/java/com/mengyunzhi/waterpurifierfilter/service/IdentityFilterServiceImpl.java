package com.mengyunzhi.waterpurifierfilter.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chuhang on 2017/6/19.
 */
@Service
public class IdentityFilterServiceImpl implements IdentityFilterService {
    @Override
    public String sha1(String data) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");

            digest.update(data.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public Boolean isTrue(String timestamp, String randomString, String encryptionInfo) {
        //拼接字符串
        String spliceString = timestamp + randomString + "mengyunzhi";
        //对拼接的字符串进行sha1加密，判断是否等于encryptionInfo
        if (this.sha1(spliceString).equals(encryptionInfo)) {
            return true;
        }
        return false;
    }
}
