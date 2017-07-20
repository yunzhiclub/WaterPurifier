package com.mengyunzhi.waterPurifierApi.repository;

import io.swagger.annotations.ApiModel;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by panjie on 17/5/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ApiModel("实体类测试根类")
public abstract class RepositoryTest {
}
