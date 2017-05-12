package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panjie on 17/5/12.
 */
@ApiModel("RechargeWaterRepository 水量充值记录实体仓库")
public interface RechargeWaterRepository extends PagingAndSortingRepository<RechargeWater, Long> {
}