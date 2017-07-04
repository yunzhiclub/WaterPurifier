package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("净水器")
    @ManyToOne
    private WaterPurifier waterPurifier;

    public WechatCustomer() {
    }

    @Override
    public String toString() {
        return "WechatCustomer{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", waterPurifier=" + waterPurifier +
                '}';
    }

    public WechatCustomer(String nickname, WaterPurifier waterPurifier) {
        this.nickname = nickname;
        this.waterPurifier = waterPurifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
