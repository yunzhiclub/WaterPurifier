package com.mengyunzhi.waterPurifierApi.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chuhang on 17-6-6.
 * 订单仓库测试实体
 */
public class OrderRepositoryTest extends RepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void save() {
        Order order = new Order();
        orderRepository.save(order);
    }

}
