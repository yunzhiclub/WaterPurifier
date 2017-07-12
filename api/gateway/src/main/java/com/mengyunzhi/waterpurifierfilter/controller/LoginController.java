package com.mengyunzhi.waterpurifierfilter.controller;

import com.mengyunzhi.waterpurifierfilter.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * Created by chuhang on 2017/7/3.
 */
@RequestMapping("/Login")
@RestController
@SessionAttributes("user")
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    private LoginService loginService;
    @GetMapping("/")
    public String Login(@RequestParam("code") String code, HttpServletRequest request) {
        //向微信服务器发送请求，获取open_id,session_key
        //请求地址https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        String openIdAndSessionKey = loginService.sendHttpToWechat(code);
        //根据openId和会话密钥 生成3rd_session
        String threeRdSession = loginService.generate3RdSession(openIdAndSessionKey);
        //以3rd_session为key，open_id+session_key为value，写入session储存
        HttpSession session = request.getSession();
        session.setAttribute(threeRdSession, openIdAndSessionKey);
        //返回3rd_session
        return threeRdSession;
    }

}