package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.Bill;
import com.mengyunzhi.waterPurifierApi.repository.BillRepository;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import org.junit.Before;
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

    @Autowired
    private WaterPurifierRepository waterPurifierRepository;

    //生成一条订单记录，用于测试
    @Before
    public void testData() {
        WaterPurifier waterPurifier = new WaterPurifier();
        waterPurifierRepository.save(waterPurifier);
        Bill bill = new Bill();
        bill.setRechargeWaterQuantity(23);
        bill.setWaterPurifier(waterPurifier);
        bill.setRechargeToWaterPurifier(Boolean.TRUE);
        billRepository.save(bill);
    }

    @Test
    public void getRechargeInfo() throws Exception {
        //模拟请求，获取充值信息
        this.mockMvc.perform(get("/api/getRechargeInfo/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","1"))
                .andDo(print())
                .andDo(document("api_getRechargeInfo", preprocessResponse(prettyPrint())));
    }


    @Test
    public void getCurrentTime() throws Exception {
        this.mockMvc.perform(get("/api/getCurrentTime")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andDo(document("api_getCurrentTime", preprocessResponse(prettyPrint())));
    }

}