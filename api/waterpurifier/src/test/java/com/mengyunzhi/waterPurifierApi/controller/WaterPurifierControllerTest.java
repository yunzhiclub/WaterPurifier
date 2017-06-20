package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static sun.nio.cs.Surrogate.is;


/**
 * Created by chuhang on 2017/6/12.
 * 净水器控制器测试
 */
public class WaterPurifierControllerTest extends ControllerTest {
    @Autowired
    private WaterPurifierRepository waterPurifierRepository;
    @Test
    public void getTest() throws Exception {
        //添加一个实体
        WaterPurifier waterPurifier = new WaterPurifier();
        waterPurifierRepository.save(waterPurifier);
        //请求查询这个实体
        this.mockMvc.perform(get("/WaterPurifier")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","23")
                .param("timestamp", "1497457292548")
                .param("randomString","unzdtggyopn1fl7sx68b8olxr")
                .param("encryptionInfo", "608c1c5acda5f1abc46a73bc181bf60b62ad604d"))
                .andDo(print())
                .andDo(document("WaterPurifier_", preprocessResponse(prettyPrint())));

        //断言查询成功
    }
}