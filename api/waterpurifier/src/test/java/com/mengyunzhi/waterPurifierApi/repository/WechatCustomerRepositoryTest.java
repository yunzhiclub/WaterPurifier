package com.mengyunzhi.waterPurifierApi.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by chuhang on 2017/7/4.
 * 微信客户实体仓库测试
 */
public class WechatCustomerRepositoryTest extends RepositoryTest{
    @Autowired
    private WechatCustomerRepository wechatCustomerRepository;

    @Test
    public void save() {
        WechatCustomer wechatCustomer = new WechatCustomer();
        wechatCustomer.setId("olyYc0eYmPvEY6McyR4QxfMZjiD8");
        wechatCustomerRepository.save(wechatCustomer);
    }


}