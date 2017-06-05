package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chuhang on 17-6-5.
 */
@ApiModel("OrderRepository 订单实体仓库")
public interface OrderRepository extends PagingAndSortingRepository<Customer, Long> {
}