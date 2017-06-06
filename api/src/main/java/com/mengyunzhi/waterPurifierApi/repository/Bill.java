package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chuhang on 17-6-6.
 */
@Entity
@ApiModel("订单实体")
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("充值金额（分）") private int rechargeAmount;
    @ApiModelProperty("充值水量（ml）") private int rechargeWaterQuantity;
    @ApiModelProperty("已冲值到机器") private Boolean isRechargeToWaterPurifier;
    @ApiModelProperty("充值到净水机时间") private Long rechargeToWaterPurifierTime;
    @ApiModelProperty("创建时间") private Long createTime;
    @ApiModelProperty("更新时间") private Long updateTime;

    @ManyToOne
    @ApiModelProperty("饮水机实体")
    private WaterPurifier waterPurifier = new WaterPurifier();

    @ManyToOne
    @ApiModelProperty("用户实体")
    private User user = new User();

    public Bill(int rechargeAmount, int rechargeWaterQuantity, Boolean isRechargeToWaterPurifier, Long rechargeToWaterPurifierTime, Long createTime, Long updateTime, WaterPurifier waterPurifier, User user) {
        this.rechargeAmount = rechargeAmount;
        this.rechargeWaterQuantity = rechargeWaterQuantity;
        this.isRechargeToWaterPurifier = isRechargeToWaterPurifier;
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.waterPurifier = waterPurifier;
        this.user = user;
    }

    public Bill() {
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", rechargeAmount=" + rechargeAmount +
                ", rechargeWaterQuantity=" + rechargeWaterQuantity +
                ", isRechargeToWaterPurifier=" + isRechargeToWaterPurifier +
                ", rechargeToWaterPurifierTime=" + rechargeToWaterPurifierTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", waterPurifier=" + waterPurifier +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(int rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public int getRechargeWaterQuantity() {
        return rechargeWaterQuantity;
    }

    public void setRechargeWaterQuantity(int rechargeWaterQuantity) {
        this.rechargeWaterQuantity = rechargeWaterQuantity;
    }

    public Boolean getRechargeToWaterPurifier() {
        return isRechargeToWaterPurifier;
    }

    public void setRechargeToWaterPurifier(Boolean rechargeToWaterPurifier) {
        isRechargeToWaterPurifier = rechargeToWaterPurifier;
    }

    public Long getRechargeToWaterPurifierTime() {
        return rechargeToWaterPurifierTime;
    }

    public void setRechargeToWaterPurifierTime(Long rechargeToWaterPurifierTime) {
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public WaterPurifier getWaterPurifier() {
        return waterPurifier;
    }

    public void setWaterPurifier(WaterPurifier waterPurifier) {
        this.waterPurifier = waterPurifier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
