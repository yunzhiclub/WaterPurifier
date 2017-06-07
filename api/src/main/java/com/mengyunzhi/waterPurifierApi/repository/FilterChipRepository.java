package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chuhang on 17-6-6.
 * 滤芯实体仓库
 */
@ApiModel("滤芯实体仓库")
public interface FilterChipRepository extends PagingAndSortingRepository<FilterChip, Long>{
}
