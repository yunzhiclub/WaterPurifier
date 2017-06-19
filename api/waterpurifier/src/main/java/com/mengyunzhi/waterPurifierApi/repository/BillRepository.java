package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chuhang on 17-6-6.
 * 订单实体仓库
 */
@ApiModel("订单实体仓库")
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
}
