package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.service.BillService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @ApiOperation(value = "getCurrentTime 获取当前时间", nickname = "api_getCurrentTime")
    @GetMapping("/getCurrentTime")
    public String getCurrentTime() {
        long time = System.currentTimeMillis();
        String timestamp = String.valueOf(time/1000);
        return timestamp;
    }


    @ApiOperation(value = "getRechargeInfo 获取充值信息", nickname = "api_getRechargeInfo")
    @GetMapping("/getRechargeInfo/")
    public int getRechargeInfo(@ApiParam(value = "净水器编号") @RequestParam("id") Long id) {
        int rechargeWaterQuantity = billService.getRechargeInfoById(id);
        return rechargeWaterQuantity;
    }

    @ApiOperation(value = "isRechargeOk 是否充值成功", nickname = "api_isRechargeOk")
    @PostMapping("/isRechargeOk")
    public void isRechargeOk(@ApiParam(value = "是否充值成功") @RequestBody RechargeResult rechargeResult) {
        //判断是否充值成功
        if (rechargeResult.getShouldRecharge() == rechargeResult.getActualRecharge()) {
            //充值成功，将该净水器订单状态为1（处理中）修改为2（已处理）
            billService.dealBill(rechargeResult.getId());
        }
        return;
    }

    @ApiModel("rechargeResult 充值结果")
    public static class RechargeResult {
        @ApiModelProperty("净水器编号")
        private Long id;
        @ApiModelProperty("应该充值水量")
        private int shouldRecharge;
        @ApiModelProperty("实际充值水量")
        private int actualRecharge;

        public RechargeResult() {
        }

        public RechargeResult(Long id, int shouldRecharge, int actualRecharge) {
            this.id = id;
            this.shouldRecharge = shouldRecharge;
            this.actualRecharge = actualRecharge;
        }

        @Override
        public String toString() {
            return "rechargeResult{" +
                    "id=" + id +
                    ", shouldRecharge=" + shouldRecharge +
                    ", actualRecharge=" + actualRecharge +
                    '}';
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getShouldRecharge() {
            return shouldRecharge;
        }

        public void setShouldRecharge(int shouldRecharge) {
            this.shouldRecharge = shouldRecharge;
        }

        public int getActualRecharge() {
            return actualRecharge;
        }

        public void setActualRecharge(int actualRecharge) {
            this.actualRecharge = actualRecharge;
        }
    }
}
