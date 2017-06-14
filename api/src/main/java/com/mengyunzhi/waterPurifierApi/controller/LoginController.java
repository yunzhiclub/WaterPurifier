package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import io.swagger.annotations.*;
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
    public String login(@ApiParam(value = "登录") @RequestBody UserLoginInfo userLoginInfo) {
        logger.info("---- 验证邓丽君信息是否合法 -----");
        return "test";
    }

    @ApiModel("UserRequestInfo 用户登录信息")
    private static class UserLoginInfo {
        @ApiModelProperty("饮水机ID")
        private Long id;
        @ApiModelProperty("加密信息")
        private String encryptionInfo;

        public UserLoginInfo() {
        }

        public Long getId() {

            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEncryptionInfo() {
            return encryptionInfo;
        }

        public void setEncryptionInfo(String encryptionInfo) {
            this.encryptionInfo = encryptionInfo;
        }
    }
}
