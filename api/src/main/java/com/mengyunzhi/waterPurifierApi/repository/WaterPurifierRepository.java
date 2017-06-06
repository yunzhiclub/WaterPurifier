package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panjie on 17/5/12.
 * 净水器实体仓库
 */
@ApiModel("净水器实体仓库")
public interface WaterPurifierRepository extends PagingAndSortingRepository<WaterPurifier, Long> {
}