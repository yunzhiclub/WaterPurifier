package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.repository.Bill;
import com.mengyunzhi.waterPurifierApi.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chuhang on 17-6-29.
 * 订单ServiceImpl
 */
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Integer getRechargeInfoById(Long id) {
        //查找未充值的订单记录
        Boolean isRechargeToWaterPurifier = Boolean.TRUE;
        List<Bill> lists = new ArrayList<>();
        lists = (List<Bill>) billRepository.findByWaterPurifierIdAndIsRechargeToWaterPurifier(id, isRechargeToWaterPurifier);

        //判断此净水器是否有充值记录
        if ( lists != null) {
            //如果此净水器有多条充值记录，将充值的水量累加
            List<Integer> rechargeWaterQuantity = new ArrayList<>();
            lists.forEach(list->{
                rechargeWaterQuantity.add(list.getRechargeWaterQuantity());
            });

            //将充值的水量累加，并返回
            return rechargeWaterQuantity.stream().mapToInt(Integer::intValue).sum();
        }
        return null;

    }
}
