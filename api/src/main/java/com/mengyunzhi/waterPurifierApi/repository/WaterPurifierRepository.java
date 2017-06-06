package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panjie on 17/5/12.
 */
@ApiModel("净水器实体")
public interface WaterPurifierRepository extends PagingAndSortingRepository<WaterPurifier, Long> {
}