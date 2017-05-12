package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/5/12.
 */
@Entity
@ApiModel("RechargeWater 水量充值记录")
public class RechargeWater implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @ApiModelProperty("饮水机实体 manyToOne")
    private WaterPurifier waterPurifier;

    @ApiModelProperty("充值定单生成时间")
    private int time;

    @ApiModelProperty("充值水量 ml")
    private int value;

    @ApiModelProperty("充值状态: false未充值，true已充值")
    private boolean status = false;

    @ApiModelProperty("成功充值时间")
    private int chargeTime;

    @ApiModelProperty("具体进行充值的数据交互ID")
    private AccessDetail accessDetail;


    public RechargeWater() {
    }

    public RechargeWater(WaterPurifier waterPurifier, int time, int value, boolean status, int chargeTime, AccessDetail accessDetail) {
        this.waterPurifier = waterPurifier;
        this.time = time;
        this.value = value;
        this.status = status;
        this.chargeTime = chargeTime;
        this.accessDetail = accessDetail;
    }

    @Override
    public String toString() {
        return "RechargeWater{" +
                "id=" + id +
                ", waterPurifier=" + waterPurifier +
                ", time=" + time +
                ", value=" + value +
                ", status=" + status +
                ", chargeTime=" + chargeTime +
                ", accessDetail=" + accessDetail +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WaterPurifier getWaterPurifier() {
        return waterPurifier;
    }

    public void setWaterPurifier(WaterPurifier waterPurifier) {
        this.waterPurifier = waterPurifier;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(int chargeTime) {
        this.chargeTime = chargeTime;
    }

    public AccessDetail getAccessDetail() {
        return accessDetail;
    }

    public void setAccessDetail(AccessDetail accessDetail) {
        this.accessDetail = accessDetail;
    }
}
