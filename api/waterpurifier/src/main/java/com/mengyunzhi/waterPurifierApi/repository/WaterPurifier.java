package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.cglib.core.GeneratorStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/5/12.
 * 净水器实体
 */
@Entity
@ApiModel("净水器实体")
public class WaterPurifier implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("状态") private String status;
    @ApiModelProperty("类型")  private String type;
    @ApiModelProperty("最近更新时间")  private Long lastUpdateTime;
    @ApiModelProperty("创建时间")  private Long createTime;
    @ApiModelProperty("更新时间")  private Long updateTime;

    public WaterPurifier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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

    @Override
    public String toString() {
        return "WaterPurifier{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public WaterPurifier(String status, String type, Long lastUpdateTime, Long createTime, Long updateTime) {
        this.status = status;
        this.type = type;
        this.lastUpdateTime = lastUpdateTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
