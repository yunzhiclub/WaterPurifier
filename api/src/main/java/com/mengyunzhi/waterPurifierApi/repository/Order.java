package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chuhang on 17-6-5.
 */
@Entity
@ApiModel("订单实体")
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ApiModelProperty("订单编号") private Long number;
    @ApiModelProperty("充值金额（分）") private int rechargeAmount;
    @ApiModelProperty("充值水量（ml）") private int rechargeWaterQuantity;
    @ApiModelProperty("已冲值到机器") private Boolean isRechargeToWaterPurifier;
    @ApiModelProperty("充值到净水机时间") private Long rechargeToWaterPurifierTime;
    @ApiModelProperty("创建时间") private Long createTime;
    @ApiModelProperty("更新时间") private Long updateTime;

    @ManyToOne
    @ApiModelProperty("饮水机实体")
    private WaterPurifier waterPurifier;

    @ManyToOne
    @ApiModelProperty("用户实体")
    private User user;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    @Override
    public String toString() {
        return "Order{" +
                "Id=" + Id +
                ", number=" + number +
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

    public Order(Long number, int rechargeAmount, int rechargeWaterQuantity, Boolean isRechargeToWaterPurifier, Long rechargeToWaterPurifierTime, Long createTime, Long updateTime, WaterPurifier waterPurifier, User user) {
        this.number = number;
        this.rechargeAmount = rechargeAmount;
        this.rechargeWaterQuantity = rechargeWaterQuantity;
        this.isRechargeToWaterPurifier = isRechargeToWaterPurifier;
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.waterPurifier = waterPurifier;
        this.user = user;
    }

    public Order() {

    }
}