package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import com.mengyunzhi.waterPurifierApi.service.WaterPurifierService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


/**
 * Created by chuhang on 2017/6/12.
 * 净水器控制器测试
 */
public class WaterPurifierControllerTest extends ControllerTest {
    @Autowired
    private WaterPurifierRepository waterPurifierRepository;
    @Autowired
    private WaterPurifierService waterPurifierService;

    @Test
    public void getTest() throws Exception {
        //保存实体，用于猜测是
        waterPurifierService.save();
        //请求查询这个实体
        this.mockMvc.perform(get("/WaterPurifier/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","23"))
                //.param("timestamp", "1497457292548")
                //.param("randomString","unzdtggyopn1fl7sx68b8olxr")
               //.param("encryptionInfo", "37cde59cfa3384c84d9bf7545be348bc880c79bd"))
                .andDo(print())
                .andDo(document("WaterPurifier_", preprocessResponse(prettyPrint())));

        //断言查询成功
    }
}