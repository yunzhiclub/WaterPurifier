package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chuhang on 2017/7/4.
 * 微信客户实体仓库
 */
@ApiModel("微信客户实体仓库")
public interface WechatCustomerRepository extends PagingAndSortingRepository<WechatCustomer, Long> {
    //查找微信客户
    WechatCustomer findById(String id);
}
