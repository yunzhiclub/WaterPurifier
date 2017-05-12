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

    @ApiModelProperty("状态:0正常，1不正常") private Boolean status;
    @ApiModelProperty("激活时间") private int activatedTime;
    @ApiModelProperty("使用用户") @ManyToOne private Customer customer;


}
