package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.type.descriptor.sql.SmallIntTypeDescriptor;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chuhang on 17-6-6.
 * 订单实体
 */
@Entity
@ApiModel("订单实体")
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    private Long id;

    @ApiModelProperty("充值金额（分）") private int rechargeAmount;
    @ApiModelProperty("充值水量（ml）") private int rechargeWaterQuantity;
    @ApiModelProperty("充值到净水机时间") private Long rechargeToWaterPurifierTime;
    @ApiModelProperty("是否充值到净水器。0：否。1：是") private int isRechargeToWaterPurifier;
    @ApiModelProperty("订单状态，0：待支付。1：已支付") private int status;
    @ApiModelProperty("创建时间") private Long createTime;
    @ApiModelProperty("更新时间") private Long updateTime;

    @ManyToOne
    @ApiModelProperty("净水器实体")
    private WaterPurifier waterPurifier;

    @ManyToOne
    @ApiModelProperty("客户实体")
    private WechatCustomer wechatCustomer;

    @PrePersist
    protected void onCreate() {
        createTime = System.currentTimeMillis();
    }

    public Bill() {
    }

    public Bill(int rechargeAmount, int rechargeWaterQuantity, Long rechargeToWaterPurifierTime, int isRechargeToWaterPurifier, int status, Long createTime, Long updateTime, WaterPurifier waterPurifier, WechatCustomer wechatCustomer) {
        this.rechargeAmount = rechargeAmount;
        this.rechargeWaterQuantity = rechargeWaterQuantity;
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
        this.isRechargeToWaterPurifier = isRechargeToWaterPurifier;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.waterPurifier = waterPurifier;
        this.wechatCustomer = wechatCustomer;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", rechargeAmount=" + rechargeAmount +
                ", rechargeWaterQuantity=" + rechargeWaterQuantity +
                ", rechargeToWaterPurifierTime=" + rechargeToWaterPurifierTime +
                ", isRechargeToWaterPurifier=" + isRechargeToWaterPurifier +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", waterPurifier=" + waterPurifier +
                ", wechatCustomer=" + wechatCustomer +
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

    public Long getRechargeToWaterPurifierTime() {
        return rechargeToWaterPurifierTime;
    }

    public void setRechargeToWaterPurifierTime(Long rechargeToWaterPurifierTime) {
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
    }

    public int getIsRechargeToWaterPurifier() {
        return isRechargeToWaterPurifier;
    }

    public void setIsRechargeToWaterPurifier(int isRechargeToWaterPurifier) {
        this.isRechargeToWaterPurifier = isRechargeToWaterPurifier;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public WechatCustomer getWechatCustomer() {
        return wechatCustomer;
    }

    public void setWechatCustomer(WechatCustomer wechatCustomer) {
        this.wechatCustomer = wechatCustomer;
    }
}
