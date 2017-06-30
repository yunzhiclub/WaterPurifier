package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panjie on 17/5/12.
 * 净水器实体仓库
 */
@ApiModel("净水器实体仓库")
public interface WaterPurifierRepository extends PagingAndSortingRepository<WaterPurifier, Long> {
    //根据净水器编号获取净水器
    WaterPurifier findById(Long id);
}