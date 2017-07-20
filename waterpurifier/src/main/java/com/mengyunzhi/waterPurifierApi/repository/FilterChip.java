package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chuhang on 17-6-6.
 * 滤芯实体
 */
@Entity
@ApiModel("滤芯实体")
public class FilterChip implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("可用水量") private int availableWaterQuantity;
    @ApiModelProperty("安装时间") private Long installTime;
    @ApiModelProperty("创建时间") private Long createTime;
    @ApiModelProperty("更新时间") private Long updateTime;

    @ApiModelProperty("净水器")
    @ManyToOne
    private WaterPurifier waterPurifier;

    public FilterChip() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAvailableWaterQuantity() {
        return availableWaterQuantity;
    }

    public void setAvailableWaterQuantity(int availableWaterQuantity) {
        this.availableWaterQuantity = availableWaterQuantity;
    }

    public Long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Long installTime) {
        this.installTime = installTime;
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

    @Override
    public String toString() {
        return "FilterChip{" +
                "id=" + id +
                ", availableWaterQuantity=" + availableWaterQuantity +
                ", installTime=" + installTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", waterPurifier=" + waterPurifier +
                '}';
    }

    public FilterChip(int availableWaterQuantity, Long installTime, Long createTime, Long updateTime, WaterPurifier waterPurifier) {
        this.availableWaterQuantity = availableWaterQuantity;
        this.installTime = installTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.waterPurifier = waterPurifier;
    }
}
