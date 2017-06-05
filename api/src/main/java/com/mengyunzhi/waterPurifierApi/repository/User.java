package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/5/12.
 */
@Entity
@ApiModel("用户")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ApiModelProperty("openId") private String openId;
    @ApiModelProperty("昵称") private String name;

    @ManyToOne
    @ApiModelProperty("净水器")
    private WaterPurifier waterPurifier;

    public User() {
    }

    public User(String openId, String name, WaterPurifier waterPurifier) {
        this.openId = openId;
        this.name = name;
        this.waterPurifier = waterPurifier;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", openId='" + openId + '\'' +
                ", name='" + name + '\'' +
                ", waterPurifier=" + waterPurifier +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WaterPurifier getWaterPurifier() {
        return waterPurifier;
    }

    public void setWaterPurifier(WaterPurifier waterPurifier) {
        this.waterPurifier = waterPurifier;
    }
}
