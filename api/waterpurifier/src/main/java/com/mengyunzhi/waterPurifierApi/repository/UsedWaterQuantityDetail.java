package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chuhang on 17-6-5.
 * 用水量详情实体
 */
@Entity
@ApiModel("用水量详情实体")
public class UsedWaterQuantityDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("用水量") private int usedWaterQuantity;
    @ApiModelProperty("上次交互时间") private Long  lastInteractionTime;
    @ApiModelProperty("本次交互时间") private Long thisInteractionTime;
    @ApiModelProperty("净水前水质") private int usedBeforeWaterQuality;
    @ApiModelProperty("净水后水质") private int rechargeToWaterPurifierTime;
    @ApiModelProperty("创建时间") private Long createTime;

    @ManyToOne
    @ApiModelProperty("净水器")
    private WaterPurifier waterPurifier = new WaterPurifier();

    public UsedWaterQuantityDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUsedWaterQuantity() {
        return usedWaterQuantity;
    }

    public void setUsedWaterQuantity(int usedWaterQuantity) {
        this.usedWaterQuantity = usedWaterQuantity;
    }

    public Long getLastInteractionTime() {
        return lastInteractionTime;
    }

    public void setLastInteractionTime(Long lastInteractionTime) {
        this.lastInteractionTime = lastInteractionTime;
    }

    public Long getThisInteractionTime() {
        return thisInteractionTime;
    }

    public void setThisInteractionTime(Long thisInteractionTime) {
        this.thisInteractionTime = thisInteractionTime;
    }

    public int getUsedBeforeWaterQuality() {
        return usedBeforeWaterQuality;
    }

    public void setUsedBeforeWaterQuality(int usedBeforeWaterQuality) {
        this.usedBeforeWaterQuality = usedBeforeWaterQuality;
    }

    public int getRechargeToWaterPurifierTime() {
        return rechargeToWaterPurifierTime;
    }

    public void setRechargeToWaterPurifierTime(int rechargeToWaterPurifierTime) {
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public WaterPurifier getWaterPurifier() {
        return waterPurifier;
    }

    public void setWaterPurifier(WaterPurifier waterPurifier) {
        this.waterPurifier = waterPurifier;
    }

    @Override
    public String toString() {
        return "UsedWaterQuantityDetail{" +
                "id=" + id +
                ", usedWaterQuantity=" + usedWaterQuantity +
                ", lastInteractionTime=" + lastInteractionTime +
                ", thisInteractionTime=" + thisInteractionTime +
                ", usedBeforeWaterQuality=" + usedBeforeWaterQuality +
                ", rechargeToWaterPurifierTime=" + rechargeToWaterPurifierTime +
                ", createTime=" + createTime +
                ", waterPurifier=" + waterPurifier +
                '}';
    }

    public UsedWaterQuantityDetail(int usedWaterQuantity, Long lastInteractionTime, Long thisInteractionTime, int usedBeforeWaterQuality, int rechargeToWaterPurifierTime, Long createTime, WaterPurifier waterPurifier) {
        this.usedWaterQuantity = usedWaterQuantity;
        this.lastInteractionTime = lastInteractionTime;
        this.thisInteractionTime = thisInteractionTime;
        this.usedBeforeWaterQuality = usedBeforeWaterQuality;
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
        this.createTime = createTime;
        this.waterPurifier = waterPurifier;
    }
}
