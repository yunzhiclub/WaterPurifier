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
                .param("id","343")
                .param("timestamp", "143423232")
                .param("randomString","fsjldflsdfjsld2gvfd")
                .param("encryptionInfo", "fsdfsdfssdfsdfsdf23cdf"))
                .andDo(print())
                .andDo(document("Login_login", preprocessResponse(prettyPrint())));

        return;
    }

}