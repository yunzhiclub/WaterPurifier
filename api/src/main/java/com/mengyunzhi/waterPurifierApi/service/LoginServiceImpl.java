package com.mengyunzhi.waterPurifierApi.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chuhang on 2017/6/14.
 */
@Service
public class LoginServiceImpl implements LoginService {
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
    public Boolean isTrue(Long id, String timestamp, String randomString, String encryptionInfo) {
        //拼接字符串
        String spliceString = id + timestamp + randomString + "mengyunzhi";
        //对拼接的字符串进行sha1加密，判断是否等于encryptionInfo
        System.out.println(spliceString);
        System.out.println(encryptionInfo);
        System.out.println(this.sha1(spliceString));
        if (this.sha1(spliceString) == encryptionInfo) {
            return true;
        }
        return false;
    }
}
