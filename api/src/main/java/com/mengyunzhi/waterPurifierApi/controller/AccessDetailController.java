package com.mengyunzhi.waterPurifierApi.controller;

import io.swagger.annotations.*;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * Created by panjie on 17/5/12.
 */
@Api(tags = "AccessDetail 数据交互详情")
@RequestMapping("/AccessDetail")
@RestController
public class AccessDetailController {

    @ApiOperation(value = "sendDetail 发送用水量详情信息", nickname = "AccessDetail_sendDetail")
    @PostMapping("/sendDetail")
    public SendDetailOutput sendDetail(@RequestBody SendDetailInput sendDetailInput) {
        SendDetailOutput sendDetailOutput = new SendDetailOutput();
        // 净水机id
        sendDetailOutput.setId(sendDetailInput.getId());
        // 当前时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sendDetailOutput.setTimeStamp(timestamp.getTime()/1000);

        sendDetailOutput.setWaterAdd(100);
        // 充值序号
        sendDetailOutput.setOrderNum(23L);

        return sendDetailOutput;
    }

    @ApiModel("SendDetailInput 用水量详情输入")
    private static class SendDetailInput {
        @ApiModelProperty("饮水机ID")
        private Long id;

        @ApiModelProperty("上次交互时间")
        private int lastSubmitTime;

        @ApiModelProperty("总用水量")
        private int waterLeft;

        public SendDetailInput() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getLastSubmitTime() {
            return lastSubmitTime;
        }

        public void setLastSubmitTime(int lastSubmitTime) {
            this.lastSubmitTime = lastSubmitTime;
        }

        public int getWaterLeft() {
            return waterLeft;
        }

        public void setWaterLeft(int waterLeft) {
            this.waterLeft = waterLeft;
        }
    }

    @ApiModel("SendDetailOutput 用水量详情输出")
    private static class SendDetailOutput {
        @ApiModelProperty("机器ID")
        private Long id;

        @ApiModelProperty("服务器当前时间")
        private Long timeStamp;

        @ApiModelProperty("净增水量（ml），应该充值水量")
        private int waterAdd;

        @ApiModelProperty("充值序号（数据交互id）")
        private Long orderNum;

        public SendDetailOutput() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(Long timeStamp) {
            this.timeStamp = timeStamp;
        }

        public int getWaterAdd() {
            return waterAdd;
        }

        public void setWaterAdd(int waterAdd) {
            this.waterAdd = waterAdd;
        }

        public Long getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(Long orderNum) {
            this.orderNum = orderNum;
        }
    }

    @PostMapping("rechargeStatus")
    @ApiOperation(value = "rechargeStatus 充值状态", nickname = "AccessDetail_rechargeStatus")
    public void rechargeStatus (@RequestBody RechargeStatusInput rechargeStatusInput){
        return;
    }

    @ApiModel("RechargeStatusInput 充值状态输入")
    private static class RechargeStatusInput {
        @ApiModelProperty("机器号")
        private Long id;

        @ApiModelProperty("充值序号（数据交互id）")
        private Long orderNum;

        @ApiModelProperty("充值状态:success成功，fail失败")
        private String result;

        public RechargeStatusInput() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(Long orderNum) {
            this.orderNum = orderNum;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
