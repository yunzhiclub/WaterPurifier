package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by chuhang on 17-6-5.
 * 用水量详情实体仓库
 */
@ApiModel("用水量详情实体仓库")
public interface UsedWaterQuantityDetailRepository extends PagingAndSortingRepository<UsedWaterQuantityDetail, Long>{
    //根据净水器编号获取最近一次的用水量详情
    UsedWaterQuantityDetail findTopByWaterPurifierIdOrderByCreateTimeDesc(Long id);
    //根据当天的最大、最小时间戳和净水器编号获取用数量详情
    List<UsedWaterQuantityDetail> findByWaterPurifierIdAndCreateTimeBetween(Long id, Long start, Long end);
}
