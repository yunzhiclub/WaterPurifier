package com.mengyunzhi.waterPurifierApi.controller;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by chuhang on 17-6-7.
 * 用水量详情控制器测试
 */
public class UsedWaterQuantityDetailControllerTest extends ControllerTest {

    @Test
    public void save() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("usedWaterQuantity", 100);
        jsonObject.put("lastInteractionTime", 142323325);
        jsonObject.put("thisInteractionTime", 142323343);
        jsonObject.put("usedBeforeWaterQuality", 100);
        jsonObject.put("rechargeToWaterPurifierTime", 142378732);
        jsonObject.put("createTime", 142335432);

        this.mockMvc.perform(post("/UsedWaterQuantityDetail/save")
                .contentType(MediaType.APPLICATION_JSON).content(jsonObject.toString()))
                .andDo(print())
                .andDo(document("UsedWaterQuantityDetail_save", preprocessResponse(prettyPrint())));
    }

}