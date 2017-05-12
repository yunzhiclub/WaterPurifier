package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panjie on 17/5/12.
 */
@ApiModel("WaterPurifierRepository 净水机实体仓库")
public interface WaterPurifierRepository extends PagingAndSortingRepository<WaterPurifier, Long> {
}