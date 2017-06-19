package com.mengyunzhi.waterPurifierApi.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.logging.Logger;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by chuhang on 2017/6/13.
 * 登录控制器测试
 */
public class LoginControllerTest extends ControllerTest {
    private static Logger logger = Logger.getLogger(WaterPurifierController.class.getName());

    @Test
    public void login() throws Exception {
        logger.info("test");
        //测试login方法
        this.mockMvc.perform(get("/Login/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","23")
                .param("timestamp", "1497457292548")
                .param("randomString","unzdtggyopn1fl7sx68b8olxr")
                .param("encryptionInfo", "608c1c5acda5f1abc46a73bc181bf60b62ad604d"))
                .andDo(print())
                .andDo(document("Login_login", preprocessResponse(prettyPrint())));

        return;
    }

}