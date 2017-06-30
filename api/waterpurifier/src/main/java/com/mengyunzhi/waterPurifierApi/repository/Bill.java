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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("充值金额（分）") private int rechargeAmount;
    @ApiModelProperty("充值水量（ml）") private int rechargeWaterQuantity;
    @ApiModelProperty("充值到净水机时间") private Long rechargeToWaterPurifierTime;
    @ApiModelProperty("订单状态，0：未处理，1：处理中，2：已处理") private int status;
    @ApiModelProperty("创建时间") private Long createTime;
    @ApiModelProperty("更新时间") private Long updateTime;

    @ManyToOne
    @ApiModelProperty("饮水机实体")
    private WaterPurifier waterPurifier;

    @ManyToOne
    @ApiModelProperty("用户实体")
    private User user;

    public Bill() {
    }

    public Bill(int rechargeAmount, int rechargeWaterQuantity, Long rechargeToWaterPurifierTime, int status, Long createTime, Long updateTime, WaterPurifier waterPurifier, User user) {
        this.rechargeAmount = rechargeAmount;
        this.rechargeWaterQuantity = rechargeWaterQuantity;
        this.rechargeToWaterPurifierTime = rechargeToWaterPurifierTime;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.waterPurifier = waterPurifier;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", rechargeAmount=" + rechargeAmount +
                ", rechargeWaterQuantity=" + rechargeWaterQuantity +
                ", rechargeToWaterPurifierTime=" + rechargeToWaterPurifierTime +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", waterPurifier=" + waterPurifier +
                ", user=" + user +
                '}';
    }
}
