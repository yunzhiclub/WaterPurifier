package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panjie on 17/5/12.
 */
@ApiModel("CustomerRepository 客户实体仓库")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long > {
}