package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Created by chuhang on 2017/6/28.
 * 登录控制器
 */
@Api(tags = "Login 登录")
@RequestMapping("/Login")
@RestController
public class LoginController {
    private static Logger logger = Logger.getLogger(WaterPurifierController.class.getName());
    @Autowired
    private LoginService loginService;


    @ApiOperation(value = "Login 登录", nickname = "Login_Login")
    @GetMapping("/")
    public String Login(@ApiParam(value = "登录凭证") @RequestParam("code") String code) {
        //向微信服务器发送请求，获取open_id,session_key
        //请求地址https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        String openIdAndSessionKey = loginService.sendHttpToWechat(code);
        //根据openId和会话密钥 生成3rd_session
        //String threeRdSession = loginService.generate3RdSession(openIdAndSessionKey);
        //以3rd_session为key，open_id+session_key为value，写入session储存

        //返回3rd_session，如果用户已绑定净水器，则一并将净水器信息返回。反之
        return openIdAndSessionKey;
    }
}
