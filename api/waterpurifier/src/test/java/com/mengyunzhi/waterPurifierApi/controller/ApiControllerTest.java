package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.Bill;
import com.mengyunzhi.waterPurifierApi.repository.BillRepository;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
/**
 * Created by chuhang on 17-6-29.
 */
public class ApiControllerTest extends ControllerTest{
    private static Logger logger = Logger.getLogger(ApiControllerTest.class.getName());

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
        bill.setId(1L);
        bill.setRechargeWaterQuantity(23);
        bill.setWaterPurifier(waterPurifier);
        bill.setStatus(1);
        billRepository.save(bill);
    }

    @Test
    public void isRechargeOk() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "18");
        jsonObject.put("shouldRecharge", 200);
        jsonObject.put("actualRecharge", 200);
        jsonObject.put("timestamp", 1497457292548L);
        jsonObject.put("randomString", "unzdtggyopn1fl7sx68b8olxr");
        jsonObject.put("encryptionInfo", "37cde59cfa3384c84d9bf7545be348bc880c79bd");

        this.mockMvc.perform(post("/api/isRechargeOk")
                .contentType(MediaType.APPLICATION_JSON).content(jsonObject.toString()))
                .andDo(print())
                .andDo(document("api_isRechargeOk", preprocessResponse(prettyPrint())));

    }

    @Test
    public void getRechargeInfo() throws Exception {
        //模拟请求，获取充值信息
        this.mockMvc.perform(get("/api/getRechargeInfo")
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

    @Test
    public void useInfo() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "1");
        jsonObject.put("usedBeforeWaterQuality", 2);
        jsonObject.put("usedAfterWaterQuality", 3);
        jsonObject.put("usedWaterQuantity", 4);
        jsonObject.put("lastInteractTime", 5);
        jsonObject.put("timestamp", 1497457292548L);
        jsonObject.put("randomString", "unzdtggyopn1fl7sx68b8olxr");
        jsonObject.put("encryptionInfo", "37cde59cfa3384c84d9bf7545be348bc880c79bd");


        this.mockMvc.perform(post("/api/useInfo")
                .contentType(MediaType.APPLICATION_JSON).content(jsonObject.toString()))
                .andDo(print())
                .andDo(document("api_useInfo", preprocessResponse(prettyPrint())));
    }

}