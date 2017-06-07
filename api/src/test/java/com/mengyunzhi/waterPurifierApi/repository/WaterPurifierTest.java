package com.mengyunzhi.waterPurifierApi.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by chuhang on 17-6-6.
 */
public class WaterPurifierTest extends RepositoryTest{
    private Logger logger = Logger.getLogger(WaterPurifierRepository.class.getName());

    //净水器实体仓库
    @Autowired
    private WaterPurifierRepository waterPurifierRepository;

    @Test
    public void save() {
        logger.info("保存测试");
        WaterPurifier waterPurifier = new WaterPurifier();
        waterPurifierRepository.save(waterPurifier);

        assertThat(waterPurifier.getId()).isNotNull();
    }

}