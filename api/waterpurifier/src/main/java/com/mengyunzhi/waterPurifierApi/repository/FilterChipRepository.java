package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chuhang on 17-6-6.
 * 滤芯实体仓库
 */
@ApiModel("滤芯实体仓库")
public interface FilterChipRepository extends PagingAndSortingRepository<FilterChip, Long>{
    //根据净水器编号获取最后一次安装的滤芯
    FilterChip findTopByWaterPurifierIdOrderByInstallTimeDesc(Long id);
}
