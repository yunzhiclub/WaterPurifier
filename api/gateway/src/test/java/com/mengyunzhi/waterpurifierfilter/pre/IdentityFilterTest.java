package com.mengyunzhi.waterpurifierfilter.pre;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by chuhang on 2017/6/19.
 */
public class IdentityFilterTest {
    @Autowired
    protected MockMvc mockMvc;

//    @Test
//    public void run() throws Exception {
//        //测试过滤方法是否生效
//        this.mockMvc.perform(get("WaterPurifier/test")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id","23")
//                .param("timestamp", "1497457292548")
//                .param("randomString","unzdtggyopn1fl7sx68b8olxr")
//                .param("encryptionInfo", "608c1c5acda5f1abc46a73bc181bf60b62ad604d"))
//                .andDo(print());
//
//        return;
//    }
}