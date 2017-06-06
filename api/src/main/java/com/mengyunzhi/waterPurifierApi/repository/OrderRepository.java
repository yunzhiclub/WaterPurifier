package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chuhang on 17-6-5.
 */
@ApiModel("订单实体")
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
}