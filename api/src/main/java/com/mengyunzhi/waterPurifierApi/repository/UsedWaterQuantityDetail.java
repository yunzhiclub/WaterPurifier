package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chuhang on 17-6-5.
 */
@Entity
@ApiModel("用水量详情")
public class UsedWaterQuantityDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ApiModelProperty("用水量") private String usedWaterQuantity;
    @ApiModelProperty("上次交互时间") private String  lastInteractionTime;
    @ApiModelProperty("本次交互时间") private String thisInteractionTime;
    @ApiModelProperty("净水前水质") private String usedBeforeWaterQuality;
    @ApiModelProperty("净水后水质") private String rechargeToWaterPurifierTime;
    @ApiModelProperty("创建时间") private String createTime;

    @ManyToOne
    @ApiModelProperty("净水器")
    private WaterPurifier waterPurifier;

    public UsedWaterQuantityDetail() {
    }

    public UsedWaterQuantityDetail(String usedWaterQuantity, String lastInteractionTime, String thisInteractionTime, String usedBeforeWaterQuality, String rechargeToWaterPurifierTime, String createTime, WaterPurifier waterPurifier) {
        this.usedWaterQuantity = usedWaterQuantity;
        this.lastInteractionTime = lastInteractionTime;
        this.thisInteractionTime = thisInteractionTime;
        this.usedBeforeWaterQuality = usedBeforeWaterQuality;
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
        this.createTime = createTime;
        this.waterPurifier = waterPurifier;
    }

    @Override
    public String toString() {
        return "UsedWaterQuantityDetail{" +
                "Id=" + Id +
                ", usedWaterQuantity='" + usedWaterQuantity + '\'' +
                ", lastInteractionTime='" + lastInteractionTime + '\'' +
                ", thisInteractionTime='" + thisInteractionTime + '\'' +
                ", usedBeforeWaterQuality='" + usedBeforeWaterQuality + '\'' +
                ", rechargeToWaterPurifierTime='" + rechargeToWaterPurifierTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", waterPurifier=" + waterPurifier +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsedWaterQuantity() {
        return usedWaterQuantity;
    }

    public void setUsedWaterQuantity(String usedWaterQuantity) {
        this.usedWaterQuantity = usedWaterQuantity;
    }

    public String getLastInteractionTime() {
        return lastInteractionTime;
    }

    public void setLastInteractionTime(String lastInteractionTime) {
        this.lastInteractionTime = lastInteractionTime;
    }

    public String getThisInteractionTime() {
        return thisInteractionTime;
    }

    public void setThisInteractionTime(String thisInteractionTime) {
        this.thisInteractionTime = thisInteractionTime;
    }

    public String getUsedBeforeWaterQuality() {
        return usedBeforeWaterQuality;
    }

    public void setUsedBeforeWaterQuality(String usedBeforeWaterQuality) {
        this.usedBeforeWaterQuality = usedBeforeWaterQuality;
    }

    public String getRechargeToWaterPurifierTime() {
        return rechargeToWaterPurifierTime;
    }

    public void setRechargeToWaterPurifierTime(String rechargeToWaterPurifierTime) {
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public WaterPurifier getWaterPurifier() {
        return waterPurifier;
    }

    public void setWaterPurifier(WaterPurifier waterPurifier) {
        this.waterPurifier = waterPurifier;
    }
}
