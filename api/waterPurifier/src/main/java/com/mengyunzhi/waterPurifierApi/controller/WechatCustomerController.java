package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomer;
import com.mengyunzhi.waterPurifierApi.repository.WechatCustomerRepository;
import com.mengyunzhi.waterPurifierApi.service.WaterPurifierService;
import com.mengyunzhi.waterPurifierApi.service.WechatCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private WechatCustomerService wechatCustomerService;

    @ApiOperation(value = "isBind 判断是否绑定净水器", nickname = "WechatCustomer_isBind")
    @GetMapping("/isBind")
    public Object isBind(@ApiParam(value = "请求信息") HttpServletRequest request) {
        String openid = request.getHeader("openid");
        //判断是否获取到用户的openid
        if (openid == "") {
            return "error";
        }
        //判断用户是否绑定净水器
        WechatCustomer wechatCustomer = wechatCustomerRepository.findById(openid);
        if (wechatCustomer == null) {
            return Boolean.FALSE;
        }
        //根据净水器编号获取相关信息
        WaterPurifierController.WaterPurifierOutput waterPurifierOutput = waterPurifierService.getRelateInfoById(wechatCustomer.getWaterPurifier().getId());

        return waterPurifierOutput;
    }

    @ApiOperation(value = "bind 绑定净水器", nickname = "WechatCustomer_bind")
    @PostMapping("/bind")
    public Boolean bind(@ApiParam(value = "客户信息实体") @RequestBody() CustomerInfo customerInfo, HttpServletRequest request) {
        String openid = request.getHeader("openid");
        return wechatCustomerService.save(openid, customerInfo);

    }

    public static class CustomerInfo {
        private Long waterPurifierId;
        private String nickName;

        public Long getWaterPurifierId() {
            return waterPurifierId;
        }

        public void setWaterPurifierId(Long waterPurifierId) {
            this.waterPurifierId = waterPurifierId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public CustomerInfo(Long waterPurifierId, String nickName) {
            this.waterPurifierId = waterPurifierId;
            this.nickName = nickName;
        }

        public CustomerInfo() {
        }
    }

}
