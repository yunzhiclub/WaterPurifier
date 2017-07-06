package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WechatCustomer;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomerRepository;
import com.mengyunzhi.waterPurifierApi.service.WaterPurifierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chuhang on 2017/7/6.
 * 微信客户控制器
 */
@Api(tags = "WechatCustomer 微信客户")
@RequestMapping("/WechatCustomer")
@RestController
public class WechatCustomerController {
    @Autowired
    private WechatCustomerRepository wechatCustomerRepository;
    @Autowired
    private WaterPurifierService waterPurifierService;

    @ApiOperation(value = "get 是否绑定净水器", nickname = "WechatCustomer_")
    @GetMapping("/")
    public Object isBind(@ApiParam(value = "openid") @RequestParam("openid") String openid) {
        //判断用户是否绑定净水器
        WechatCustomer wechatCustomer = wechatCustomerRepository.findById(openid);
        if (wechatCustomer == null) {
            return Boolean.FALSE;
        }
        //根据净水器编号获取相关信息
        WaterPurifierController.WaterPurifierOutput waterPurifierOutput = waterPurifierService.getRelateInfoById(wechatCustomer.getWaterPurifier().getId());

        return waterPurifierOutput;
    }
}
