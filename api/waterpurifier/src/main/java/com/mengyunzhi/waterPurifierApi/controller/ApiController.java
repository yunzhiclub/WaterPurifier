package com.mengyunzhi.waterPurifierApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;


/**
 * Created by chuhang on 17-6-29.
 * api接口
 */
@Api(tags = "API 接口")
@RequestMapping("/api")
@RestController
public class ApiController {
    @ApiOperation(value = "getCurrentTime 获取当前时间", nickname = "api_getCurrentTime")
    @GetMapping("/getCurrentTime")
    public String getCurrentTime() {
        long time = System.currentTimeMillis();
        String timestamp = String.valueOf(time/1000);
        return timestamp;
    }

    @ApiOperation(value = "getRechargeInfo 获取充值信息", nickname = "Api_getCurrentTime")
    @GetMapping("/getRechargeInfo")
    public TreeMap<String, Integer> getRechargeInfo() {
        return null;
    }
}
