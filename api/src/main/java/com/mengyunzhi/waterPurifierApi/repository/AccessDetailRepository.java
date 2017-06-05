package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panjie on 17/5/12.
 */
@ApiModel("AccessDetailRepository 实时数据交互实体仓库")
public interface AccessDetailRepository extends PagingAndSortingRepository<AccessDetail, Long> {
}