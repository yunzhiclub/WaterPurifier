package com.mengyunzhi.waterPurifierApi.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by chuhang on 17-6-6.
 * 订单实体仓库测试
 */
public class BillRepositoryTest extends RepositoryTest {
    private Logger logger = Logger.getLogger(BillRepositoryTest.class.getName());

    //订单实体仓库
    @Autowired
    private BillRepository billRepository;

    @Test
    public void save() {
        logger.info("----- 保存测试 -----");
        Bill bill = new Bill();
        billRepository.save(bill);

        assertThat(bill.getId()).isNotNull();
    }

}