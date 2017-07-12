package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.UsedWaterQuantityDetail;
import com.mengyunzhi.waterPurifierApi.service.BillService;
import com.mengyunzhi.waterPurifierApi.service.UsedWaterQuantityDetailService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


/**
 * Created by chuhang on 17-6-29.
 * api接口
 */
@Api(tags = "API 接口")
@RequestMapping("/api")
@RestController
public class ApiController {
    private static Logger logger = Logger.getLogger(ApiController.class.getName());

    @Autowired
    private BillService billService;

    @Autowired
    private UsedWaterQuantityDetailService usedWaterQuantityDetailService;

    @ApiOperation(value = "getCurrentTime 获取服务器当前时间", nickname = "api_getCurrentTime")
    @GetMapping("/getCurrentTime")
    public String getCurrentTime() {
        long time = System.currentTimeMillis();
        String timestamp = String.valueOf(time / 1000);
        return timestamp;
    }


    @ApiOperation(value = "getRechargeInfo 获取充值水量", nickname = "api_getRechargeInfo")
    @GetMapping("/getRechargeInfo")
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

    @ApiOperation(value = "useInfo 使用信息", nickname = "api_useInfo")
    @PostMapping("/useInfo")
    public void useInfo(@ApiParam(value = "净水器使用信息") @RequestBody UseInfo useInfo) {
        logger.info("净水器使用信息");
        UsedWaterQuantityDetail usedWaterQuantityDetail = new UsedWaterQuantityDetail();
        usedWaterQuantityDetailService.saveUseInfo(useInfo);
        return;
    }

    @ApiModel("净水器使用信息")
    public static class UseInfo {
        @ApiModelProperty("净水器编号")
        private Long id;
        @ApiModelProperty("净水前水质")
        private int usedBeforeWaterQuality;
        @ApiModelProperty("净水后水质")
        private int usedAfterWaterQuality;
        @ApiModelProperty("用水量")
        private int usedWaterQuantity;
        @ApiModelProperty("上次交互时间")
        private Long lastInteractTime;
        @ApiModelProperty("时间戳")
        private Long timestamp;
        @ApiModelProperty("随机字符串")
        private String randomString;
        @ApiModelProperty("加密信息")
        private String encryptionInfo;

        public UseInfo() {
        }

        public UseInfo(Long id, int usedBeforeWaterQuality, int usedAfterWaterQuality, int usedWaterQuantity, Long lastInteractTime, Long timestamp, String randomString, String encryptionInfo) {
            this.id = id;
            this.usedBeforeWaterQuality = usedBeforeWaterQuality;
            this.usedAfterWaterQuality = usedAfterWaterQuality;
            this.usedWaterQuantity = usedWaterQuantity;
            this.lastInteractTime = lastInteractTime;
            this.timestamp = timestamp;
            this.randomString = randomString;
            this.encryptionInfo = encryptionInfo;
        }

        @Override
        public String toString() {
            return "UseInfo{" +
                    "id=" + id +
                    ", usedBeforeWaterQuality=" + usedBeforeWaterQuality +
                    ", usedAfterWaterQuality=" + usedAfterWaterQuality +
                    ", usedWaterQuantity=" + usedWaterQuantity +
                    ", lastInteractTime=" + lastInteractTime +
                    ", timestamp=" + timestamp +
                    ", randomString='" + randomString + '\'' +
                    ", encryptionInfo='" + encryptionInfo + '\'' +
                    '}';
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getUsedBeforeWaterQuality() {
            return usedBeforeWaterQuality;
        }

        public void setUsedBeforeWaterQuality(int usedBeforeWaterQuality) {
            this.usedBeforeWaterQuality = usedBeforeWaterQuality;
        }

        public int getUsedAfterWaterQuality() {
            return usedAfterWaterQuality;
        }

        public void setUsedAfterWaterQuality(int usedAfterWaterQuality) {
            this.usedAfterWaterQuality = usedAfterWaterQuality;
        }

        public int getUsedWaterQuantity() {
            return usedWaterQuantity;
        }

        public void setUsedWaterQuantity(int usedWaterQuantity) {
            this.usedWaterQuantity = usedWaterQuantity;
        }

        public Long getLastInteractTime() {
            return lastInteractTime;
        }

        public void setLastInteractTime(Long lastInteractTime) {
            this.lastInteractTime = lastInteractTime;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public String getRandomString() {
            return randomString;
        }

        public void setRandomString(String randomString) {
            this.randomString = randomString;
        }

        public String getEncryptionInfo() {
            return encryptionInfo;
        }

        public void setEncryptionInfo(String encryptionInfo) {
            this.encryptionInfo = encryptionInfo;
        }
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
            return "RechargeResult{" +
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
