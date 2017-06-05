package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.cglib.core.GeneratorStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/5/12.
 */
@Entity
@ApiModel("净水机")
public class WaterPurifier implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("编号") private Boolean number;
    @ApiModelProperty("状态") private int status;
    @ApiModelProperty("类型") @ManyToOne private Customer type;
    @ApiModelProperty("最近更新时间") @ManyToOne private Customer lastUpdateTime;
    @ApiModelProperty("创建时间") @ManyToOne private Customer createTime;
    @ApiModelProperty("更新时间") @ManyToOne private Customer updateTime;
    @ApiModelProperty("用户")
    @ManyToOne
    private User user;

    public WaterPurifier() {
    }

    public WaterPurifier(Boolean number, int status, Customer type, Customer lastUpdateTime, Customer createTime, Customer updateTime, User user) {
        this.number = number;
        this.status = status;
        this.type = type;
        this.lastUpdateTime = lastUpdateTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
    }

    @Override
    public String toString() {
        return "WaterPurifier{" +
                "id=" + id +
                ", number=" + number +
                ", status=" + status +
                ", type=" + type +
                ", lastUpdateTime=" + lastUpdateTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getNumber() {
        return number;
    }

    public void setNumber(Boolean number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer getType() {
        return type;
    }

    public void setType(Customer type) {
        this.type = type;
    }

    public Customer getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Customer lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Customer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Customer createTime) {
        this.createTime = createTime;
    }

    public Customer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Customer updateTime) {
        this.updateTime = updateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
