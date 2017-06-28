package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.controller.WaterPurifierController;
import io.swagger.util.Json;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.logging.Logger;

import static com.google.common.base.Predicates.equalTo;
import static org.assertj.core.api.Assertions.assertThat;
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
        return null;
    }
}
