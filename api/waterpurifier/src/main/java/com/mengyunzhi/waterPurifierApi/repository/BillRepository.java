package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chuhang on 17-6-6.
 * 订单实体仓库
 */
@ApiModel("订单实体仓库")
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
    //根据净水器编号查找未充值到净水器的订单记录
    List<Bill> findByWaterPurifierIdAndStatus(Long id, int status);
}
