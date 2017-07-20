package com.mengyunzhi.waterPurifierApi.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by panjie on 17/5/12.
 */
public class WaterPurifierRepositoryTest extends RepositoryTest{
    //净水器实体仓库
    @Autowired
    private WaterPurifierRepository waterPurifierRepository;

    @Test
    public void save() {
        WaterPurifier waterPurifier = new WaterPurifier();
        waterPurifierRepository.save(waterPurifier);
    }
}