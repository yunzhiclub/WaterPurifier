package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * Created by chuhang on 2017/6/13.
 * 登录控制器
 */
@Api(tags = "Login 登录")
@RequestMapping("/Login")
@RestController
public class LoginController {
    private static Logger logger = Logger.getLogger(WaterPurifierController.class.getName());

    @ApiOperation(value = "login 判断是否为用户发出的请求", nickname = "Login_login")
    @PostMapping("/login")
    public void get(@ApiParam(value = "登录") @RequestBody RequestInfo requestInfo) {
        logger.info("---- 验证邓丽君信息是否合法 -----");
    }
}
