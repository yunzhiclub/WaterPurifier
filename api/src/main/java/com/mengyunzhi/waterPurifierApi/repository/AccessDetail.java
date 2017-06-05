package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/5/12.
 */
@ApiModel("AccessDetail 实时数据交互实体")
@Entity
public class AccessDetail implements Serializable {
    private static Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @ApiModelProperty("饮水机实体")
    private WaterPurifier waterPurifier;
    @ApiModelProperty("交互时间")
    private int accessTime;
    @ApiModelProperty("上次交互时间")
    private int preAccessTime;
    @ApiModelProperty("机器上传总用水量 ml")
    private int waterUsage;
    @ApiModelProperty("机器上传剩余水量 ml")
    private int leftWater;

    public AccessDetail() {
    }

    public AccessDetail(WaterPurifier waterPurifier, int accessTime, int preAccessTime, int waterUsage, int leftWater) {
        this.waterPurifier = waterPurifier;
        this.accessTime = accessTime;
        this.preAccessTime = preAccessTime;
        this.waterUsage = waterUsage;
        this.leftWater = leftWater;
    }

    @Override
    public String toString() {
        return "AccessDetail{" +
                "id=" + id +
                ", waterPurifier=" + waterPurifier +
                ", accessTime=" + accessTime +
                ", preAccessTime=" + preAccessTime +
                ", waterUsage=" + waterUsage +
                ", leftWater=" + leftWater +
                '}';
    }

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(Long serialVersionUID) {
        AccessDetail.serialVersionUID = serialVersionUID;
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

    public int getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }

    public int getPreAccessTime() {
        return preAccessTime;
    }

    public void setPreAccessTime(int preAccessTime) {
        this.preAccessTime = preAccessTime;
    }

    public int getWaterUsage() {
        return waterUsage;
    }

    public void setWaterUsage(int waterUsage) {
        this.waterUsage = waterUsage;
    }

    public int getLeftWater() {
        return leftWater;
    }

    public void setLeftWater(int leftWater) {
        this.leftWater = leftWater;
    }
}