package com.mengyunzhi.waterPurifierApi.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by panjie on 17/6/6.
 * 用户仓库测试实体
 */
public class UserRepositoryTest extends RepositoryTest {
    private Logger logger = Logger.getLogger(UserRepositoryTest.class.getName());

    //用户仓库
    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        logger.info("----- 保存测试 -----");
        User user = new User();
        userRepository.save(user);

        assertThat(user.getId()).isNotNull();
    }
}