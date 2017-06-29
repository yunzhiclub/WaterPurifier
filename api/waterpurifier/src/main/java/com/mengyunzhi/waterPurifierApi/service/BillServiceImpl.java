package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.repository.Bill;
import com.mengyunzhi.waterPurifierApi.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chuhang on 17-6-29.
 * 订单ServiceImpl
 */
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public int getRechargeInfoById(Long id) {
        //查找未充值的订单记录
        Boolean Bool = false;
        Bill bill = billRepository.findByWaterPurifierIdAndIsRechargeToWaterPurifier(id, Bool);
        return bill.getRechargeWaterQuantity();
    }
}
