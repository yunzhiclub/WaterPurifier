package com.mengyunzhi.waterPurifierApi.service;

import com.mengyunzhi.waterPurifierApi.constant.Constant;
import com.mengyunzhi.waterPurifierApi.controller.WechatCustomerController;
import com.mengyunzhi.waterPurifierApi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chuhang on 17-6-29.
 * 订单ServiceImpl
 */
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private WechatCustomerRepository wechatCustomerRepository;
    @Autowired
    private WaterPurifierRepository waterPurifierRepository;

    //订单的后三位，同一秒内生成的第“n”条订单，时间戳-》n
    private Map<Long, Integer> billLast;

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


    @Override
    public Long generateBillAndGetId(String openid, WechatCustomerController.PayInfo payInfo) {
        //根据openid查找客户实体
        WechatCustomer wechatCustomer = wechatCustomerRepository.findById(openid);
        // 根据净水器id查找净水器实体
        WaterPurifier waterPurifier = waterPurifierRepository.findById(payInfo.getWaterPurifierId());
        //生成订单编号
        this.generateBillNumber();
        //生成一条订单
        Bill bill = new Bill();
        bill.setRechargeAmount(payInfo.getRechargeAmount());
        bill.setRechargeWaterQuantity(payInfo.getRechargeWaterQuantity());
        bill.setWaterPurifier(waterPurifier);
        bill.setWechatCustomer(wechatCustomer);
        bill.setId(this.generateBillNumber());

        billRepository.save(bill);
        return bill.getId();
    }

    public Long generateBillNumber() {
        //获取当前时间,格式20170717104204
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.TIME_FORMAT);
        String temp = dateFormat.format(new Date(Long.valueOf(timestamp)));
        Long date = Long.parseLong(temp);
        //获取订单后三位
        Long billLastThree = this.getBillLastThree(timestamp/1000);
        //拼接订单号并返回
        return date * 1000 + billLastThree;
    }

    @Override
    public Long getBillLastThree(Long time) {
        try {
            //判断当前时间是否已经生成订单
            if (this.billLast != null) {
                //设置别名this.billLast
                Map<Long, Integer> billLast = this.billLast;
                //同一秒内如果已经生成n个订单，则n+1
                for ( Long key : billLast.keySet() ) {
                    //获取同一秒内订单的已经生成个数
                    int n = this.billLast.get(key);
                    //初始化
                    this.billLast = new HashMap<>();
                    if (time.equals(key)) {
                        this.billLast.put(time, 1);
                    } else {
                        this.billLast.put(time, n + 1);
                    }
                }
            } else {
                this.billLast = new HashMap<>();
                this.billLast.put(time, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将订单序号转化为3位数，如22转为022
        String billLastThree = String.valueOf(this.billLast.get(time));
        while (billLastThree.length() < 3) {
            billLastThree = "0" + billLastThree;
        }
        return Long.parseLong(billLastThree);
    }

    public void setSignById(Long id, String sign) throws Exception {
        try {
            Bill bill = billRepository.findById(id);
            bill.setSign(sign);
            billRepository.save(bill);
            return;
        } catch (Exception e) {
            throw new Exception("保存签名出错");
        }
    }
}
