package com.mengyunzhi.waterPurifierApi;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by panjie on 17/5/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class WaterpurifierapiApplicationTest {
    @Autowired
    protected MockMvc mockMvc;

    // 获取用于生成自动文档的swagger.json文件
    @Test
    public void getAndSaveSwaggerJson() throws Exception {
        String content = this.mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andReturn().getResponse().getContentAsString();


        // 判断是否存在文件夹，不存在，则创建
        File swaggerDir = new File("target/swagger");
        if (!swaggerDir.exists()) {
            try {
                swaggerDir.mkdir();
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }

        // 创建文件
        File swaggerFile = new File("target/swagger/swagger.json");
        if (!swaggerFile.exists()) {
            try {
                swaggerFile.createNewFile();
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }

        // 写入swagger.json文档
        try {
            FileWriter fileWriter = new FileWriter("target/swagger/swagger.json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    @Test
    public void contextLoads() {
    }

}