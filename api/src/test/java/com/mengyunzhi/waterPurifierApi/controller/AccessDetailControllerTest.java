package com.mengyunzhi.waterPurifierApi.controller;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import static org.junit.Assert.*;

/**
 * Created by panjie on 17/5/12.
 */
public class AccessDetailControllerTest extends ControllerTest {
    @Test
    public void sendDetail() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("lastSubmitTime", 23123232);
        jsonObject.put("waterLeft", 21212);

        this.mockMvc.perform(post("/AccessDetail/sendDetail")
        .contentType(MediaType.APPLICATION_JSON).content(jsonObject.toString()))
        .andDo(print())
        .andDo(document("AccessDetail_sendDetail", preprocessResponse(prettyPrint())));
    }

    @Test
    public void rechargeStatus() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("orderNum", 23L);
        jsonObject.put("result", "success");

        this.mockMvc.perform(
                post("/AccessDetail/rechargeStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString())
        ).andDo(print())
                .andDo(document("AccessDetail_rechargeStatus", preprocessResponse(prettyPrint())));
    }

}