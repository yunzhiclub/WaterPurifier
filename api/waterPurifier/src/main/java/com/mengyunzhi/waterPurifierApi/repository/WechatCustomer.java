package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chuhang on 2017/7/4.
 * 微信客户实体
 */
@Entity
@ApiModel("微信客户实体")
public class WechatCustomer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID",unique=true, columnDefinition="VARCHAR(100)")
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("净水器")
    @ManyToOne
    private WaterPurifier waterPurifier;

    public WechatCustomer() {
    }

    public WechatCustomer(String id, String nickname, WaterPurifier waterPurifier) {
        this.id = id;
        this.nickname = nickname;
        this.waterPurifier = waterPurifier;
    }

    @Override
    public String toString() {
        return "WechatCustomer{}";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public WaterPurifier getWaterPurifier() {
        return waterPurifier;
    }

    public void setWaterPurifier(WaterPurifier waterPurifier) {
        this.waterPurifier = waterPurifier;
    }
}
