package com.mengyunzhi.waterpurifierfilter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.logging.Logger;

/**
 * Created by chuhang on 2017/7/3.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());

    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.appid}")
    private String appid;

    @Autowired
    private IdentityFilterService identityFilterService;
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
                .queryParam("appid", appid)
                .queryParam("secret", secret)
                .queryParam("js_code", code)
                .queryParam("grant_type", "authorization_code");

        ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);

        //将请求到的信息返回
        return response.getBody();
    }

    @Override
    public String generate3RdSession(String openIdAndSessionKey) {
        String spliceString = openIdAndSessionKey + "mengyunzhi";
        return identityFilterService.sha1(spliceString).substring(0, 15);
    }

}
