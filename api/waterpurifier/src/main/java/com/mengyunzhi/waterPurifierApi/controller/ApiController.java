package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.Bill;
import com.mengyunzhi.waterPurifierApi.repository.BillRepository;
import com.mengyunzhi.waterPurifierApi.service.BillService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private BillService billService;
    @Autowired
    private BillRepository billRepository;

    @ApiOperation(value = "getCurrentTime 获取当前时间", nickname = "api_getCurrentTime")
    @GetMapping("/getCurrentTime")
    public String getCurrentTime() {
        long time = System.currentTimeMillis();
        String timestamp = String.valueOf(time/1000);
        return timestamp;
    }

    @ApiOperation(value = "getRechargeInfo 获取充值信息", nickname = "api_getCurrentTime")
    @GetMapping("/getRechargeInfo/")
    public Long getRechargeInfo(@ApiParam(value = "净水器编号") @RequestParam("id") Long id) {
        Bill bill = new Bill();
        bill.setId(2L);
        bill.setRechargeToWaterPurifier(false);
        billRepository.save(bill);

        int rechargeWaterQuantity = billService.getRechargeInfoById(id);
        return id;
    }
}
