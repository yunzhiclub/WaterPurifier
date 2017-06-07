package com.mengyunzhi.waterPurifierApi.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by chuhang on 17-6-6.
 *滤芯实体测试
 */
public class FilterChipTest extends RepositoryTest{
    private Logger logger = Logger.getLogger(FilterChipRepository.class.getName());

    //滤芯实体仓库
    @Autowired
    private FilterChipRepository filterChipRepository;

    @Test
    public void save() {
        logger.info("测试save方法");
        FilterChip filterChip = new FilterChip();
        filterChipRepository.save(filterChip);
        assertThat(filterChip.getId()).isNotNull();
    }

}