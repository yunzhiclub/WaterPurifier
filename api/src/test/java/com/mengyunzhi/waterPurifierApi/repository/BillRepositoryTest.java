package com.mengyunzhi.waterPurifierApi.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by chuhang on 17-6-6.
 * 订单实体仓库测试
 */
public class BillRepositoryTest extends RepositoryTest {
    @Autowired
    private BillRepository billRepository;

    @Test
    public void save() {
        Bill bill = new Bill();
        billRepository.save(bill);
    }

}