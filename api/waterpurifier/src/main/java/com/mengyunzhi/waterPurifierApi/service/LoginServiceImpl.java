package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WaterPurifierController;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Created by chuhang on 2017/6/28.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static Logger logger = Logger.getLogger(WaterPurifierController.class.getName());

    @Override
    public String sendHttpToWechat(String code) {
        //设置http请求的头信息
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        //实例化restTemplate，并设置请求路由
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        //发送请求
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("appid", "wxa84c8afd480f058c")
                .queryParam("secret", "ec1c821f512da4895eac961eed649206")
                .queryParam("js_code", code)
                .queryParam("grant_type", "authorization_code");

        ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);

        //将请求到的信息返回
        return response.getBody();
    }

    @Override
    public String generate3RdSession(String openIdAndSessionKey) {
        String spliceString = openIdAndSessionKey + "mengyunzhi";
        return this.sha1(spliceString).substring(0, 15);
    }

    @Override
    public String sha1(String data) {
        try {
            logger.info("sha1加密");
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(data.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() <= 1) {
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
    public void storeSession(String threeRdSession, String openIdAndSessionKey, HttpSession session) {
        session.setAttribute(threeRdSession, openIdAndSessionKey);
        return;
    }
}
