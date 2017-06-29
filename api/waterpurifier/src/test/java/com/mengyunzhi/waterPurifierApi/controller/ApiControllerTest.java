package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.Bill;
import com.mengyunzhi.waterPurifierApi.repository.BillRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private BillRepository billRepository;

    @Test
    public void getRechargeInfo() throws Exception {
        //保存一条订单记录，用于测试
        Bill bill = new Bill();
        bill.setId(2L);
        bill.setRechargeToWaterPurifier(false);
        billRepository.save(bill);
        //模拟请求，获取充值信息
        this.mockMvc.perform(get("/api/getRechargeInfo/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","2"))
                .andDo(print())
                .andDo(document("api_getCurrentTime", preprocessResponse(prettyPrint())));

    }

    @Test
    public void getCurrentTime() throws Exception {
        this.mockMvc.perform(get("/api/getCurrentTime")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andDo(document("api_getCurrentTime", preprocessResponse(prettyPrint())));
    }

}