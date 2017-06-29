package com.mengyunzhi.waterPurifierApi.controller;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.Assert.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by chuhang on 17-6-29.
 */
public class ApiControllerTest extends ControllerTest{
    @Test
    public void getCurrentTime() throws Exception {
        this.mockMvc.perform(get("/api/getCurrentTime")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andDo(document("Api_", preprocessResponse(prettyPrint())));
    }

}