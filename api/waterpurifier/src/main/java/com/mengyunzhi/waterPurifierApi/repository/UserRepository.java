package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panjie on 17/5/12.
 * UserRepository 用户实体仓库
 */
@ApiModel("用户实体仓库")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}