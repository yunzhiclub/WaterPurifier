package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.UsedWaterQuantityDetail;
import com.mengyunzhi.waterPurifierApi.repository.UsedWaterQuantityDetailRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chuhang on 17-6-7.
 * 用水量详情控制器
 */
@Api(tags = "UsedWaterQuantityDetail 用水量详情")
@RequestMapping("/UsedWaterQuantityDetail")
@RestController
public class UsedWaterQuantityDetailController {
    @Autowired
    private UsedWaterQuantityDetailRepository usedWaterQuantityDetailRepository;

    //TODO add方法是否像之前的用于重定向页面，怎么做与净水机的借口?这个不是保存方法做的么？不理解.暂时理解为AccessDetail中的sendDetail方法实现的功能
    @ApiOperation(value = "add 添加用水量的详情", nickname = "UsedWaterQuantityDetail_add")
    @RequestMapping("/add")
    public void add() {
        return;
    }

    @ApiOperation(value = "save 保存用水量的详情", nickname = "UsedWaterQuantityDetail_save")
    @PostMapping("/save")
    public UsedWaterQuantityDetail save(@RequestBody UsedWaterQuantityDetail usedWaterQuantityDetail) {
        usedWaterQuantityDetailRepository.save(usedWaterQuantityDetail);
        return usedWaterQuantityDetail;
    }


}
