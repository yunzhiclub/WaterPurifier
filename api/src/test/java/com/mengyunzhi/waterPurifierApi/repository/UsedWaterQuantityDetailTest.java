package com.mengyunzhi.waterPurifierApi.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by chuhang on 17-6-6.
 */
public class UsedWaterQuantityDetailTest extends RepositoryTest{
    private Logger logger = Logger.getLogger(UsedWaterQuantityDetailRepository.class.getName());

    @Autowired
    private UsedWaterQuantityDetailRepository usedWaterQuantityDetailRepository;

    @Test
    public void save() {
        logger.info("保存测试");
        UsedWaterQuantityDetail usedWaterQuantityDetail = new UsedWaterQuantityDetail();
        usedWaterQuantityDetailRepository.save(usedWaterQuantityDetail);

        assertThat(usedWaterQuantityDetail.getId()).isNotNull();
    }


}