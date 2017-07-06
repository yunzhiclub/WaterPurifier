package com.mengyunzhi.waterPurifierApi.controller;

import com.mengyunzhi.waterPurifierApi.repository.WaterPurifierRepository;
import com.mengyunzhi.waterPurifierApi.service.WaterPurifierService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
    private WaterPurifierService waterPurifierService;

    @ApiOperation(value = "get 获取净水器信息", nickname = "WaterPurifier_")
    @GetMapping("/")
    public WaterPurifierOutput get(@ApiParam(value = "净水器编号") @RequestParam("id") Long id) {
        logger.info("---- 获取净水器实体信息 -----");
        //根据净水器编号获取相关信息
        WaterPurifierOutput waterPurifierOutput = waterPurifierService.getRelateInfoById(id);

        return waterPurifierOutput;
    }

    @ApiModel("WaterPurifierOutput 净水器相关信息")
    public static class WaterPurifierOutput {
        @ApiModelProperty("净水器编号")
        public Long id;
        @ApiModelProperty("今日用水量")
        public Integer todayUsedWater;
        @ApiModelProperty("剩余用水量")
        public Integer lastUsedWater;
        @ApiModelProperty("剩余滤芯")
        public Integer lastFilterChip;
        @ApiModelProperty("净水前水质状态")
        public Integer usedBeforeWaterQuality;
        @ApiModelProperty("净水后水质状态")
        public Integer usedAfterWaterQuality;
        @ApiModelProperty("最近一周用水量")
        public Map<String, Integer> recentOneWeekUsedWater;

        public WaterPurifierOutput() {
        }

        public WaterPurifierOutput(Long id, Integer todayUsedWater, Integer lastUsedWater, Integer lastFilterChip, Integer usedBeforeWaterQuality, Integer usedAfterWaterQuality, Map<String, Integer> recentOneWeekUsedWater) {
            this.id = id;
            this.todayUsedWater = todayUsedWater;
            this.lastUsedWater = lastUsedWater;
            this.lastFilterChip = lastFilterChip;
            this.usedBeforeWaterQuality = usedBeforeWaterQuality;
            this.usedAfterWaterQuality = usedAfterWaterQuality;
            this.recentOneWeekUsedWater = recentOneWeekUsedWater;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getTodayUsedWater() {
            return todayUsedWater;
        }

        public void setTodayUsedWater(Integer todayUsedWater) {
            this.todayUsedWater = todayUsedWater;
        }

        public Integer getLastUsedWater() {
            return lastUsedWater;
        }

        public void setLastUsedWater(Integer lastUsedWater) {
            this.lastUsedWater = lastUsedWater;
        }

        public Integer getLastFilterChip() {
            return lastFilterChip;
        }

        public void setLastFilterChip(Integer lastFilterChip) {
            this.lastFilterChip = lastFilterChip;
        }

        public Integer getUsedBeforeWaterQuality() {
            return usedBeforeWaterQuality;
        }

        public void setUsedBeforeWaterQuality(Integer usedBeforeWaterQuality) {
            this.usedBeforeWaterQuality = usedBeforeWaterQuality;
        }

        public Integer getUsedAfterWaterQuality() {
            return usedAfterWaterQuality;
        }

        public void setUsedAfterWaterQuality(Integer usedAfterWaterQuality) {
            this.usedAfterWaterQuality = usedAfterWaterQuality;
        }

        public Map<String, Integer> getRecentOneWeekUsedWater() {
            return recentOneWeekUsedWater;
        }

        public void setRecentOneWeekUsedWater(Map<String, Integer> recentOneWeekUsedWater) {
            this.recentOneWeekUsedWater = recentOneWeekUsedWater;
        }

        @Override
        public String toString() {
            return "WaterPurifierOutput{" +
                    "id=" + id +
                    ", todayUsedWater=" + todayUsedWater +
                    ", lastUsedWater=" + lastUsedWater +
                    ", lastFilterChip=" + lastFilterChip +
                    ", usedBeforeWaterQuality=" + usedBeforeWaterQuality +
                    ", usedAfterWaterQuality=" + usedAfterWaterQuality +
                    ", recentOneWeekUsedWater=" + recentOneWeekUsedWater +
                    '}';
        }
    }
}
