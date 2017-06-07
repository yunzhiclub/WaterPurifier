package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chuhang on 17-6-5.
 * 用水量详情实体仓库
 */
@ApiModel("用水量详情实体仓库")
public interface UsedWaterQuantityDetailRepository extends PagingAndSortingRepository<UsedWaterQuantityDetail, Long>{
}
