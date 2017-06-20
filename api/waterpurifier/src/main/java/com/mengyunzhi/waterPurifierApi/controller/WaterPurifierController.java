package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WaterPurifier;
import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * Created by chuhang on 2017/6/12.
 * 净水器控制器
 */
@Api(tags = "WaterPurifier 净水器")
@RequestMapping("/WaterPurifier")
@RestController
public class WaterPurifierController {
    private static Logger logger = Logger.getLogger(WaterPurifierController.class.getName());
    @Autowired
    private WaterPurifierRepository waterPurifierRepository;

    //TODO根据净水器编号获取今日用水量、剩余用水量、剩余滤芯、净水前水质状态、净水后水质转台、最近一周用水量、
    @ApiOperation(value = "get 获取净水器信息", nickname = "WaterPurifier_")
    @GetMapping("/")
    public String get(@ApiParam(value = "净水器编号") @RequestParam("id") String id) {
        logger.info("---- 获取净水器实体信息 -----");
        return id;
    }

}
