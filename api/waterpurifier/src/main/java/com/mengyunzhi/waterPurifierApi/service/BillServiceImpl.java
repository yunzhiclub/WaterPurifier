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
        List<Bill> lists = new ArrayList<>();
        lists = (List<Bill>) billRepository.findByWaterPurifierIdAndStatus(id, 0);

        //判断此净水器是否有充值记录
        if ( lists != null) {
            //如果此净水器有多条充值记录，将充值的水量累加，并将订单状态修改为1（处理中）
            List<Integer> rechargeWaterQuantity = new ArrayList<>();
            lists.forEach(list->{
                //修改订单状态
                list.setStatus(1);
                billRepository.save(list);
                //累加水量
                rechargeWaterQuantity.add(list.getRechargeWaterQuantity());
            });

            //将充值的水量累加，并返回
            return rechargeWaterQuantity.stream().mapToInt(Integer::intValue).sum();
        }
        return null;

    }

    @Override
    public void dealBill(Long id) {
        //根据净水器编号查找订单转台为1（处理中）的订单
        List<Bill> lists = new ArrayList<>();
        lists = (List<Bill>) billRepository.findByWaterPurifierIdAndStatus(id, 1);
        //将订单状态修改为2（已处理）
        List<Integer> rechargeWaterQuantity = new ArrayList<>();
        lists.forEach(list->{
            //修改订单状态
            list.setStatus(2);
            billRepository.save(list);
        });

        return;
    }
}
