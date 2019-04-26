package com.vmall.vtrade.service.orderservice;

import com.vmall.mapper.ordermapper.VOrderMapper;
import com.vmall.pojo.VOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VOrderService {
    @Autowired
    VOrderMapper vOrderMapper;

    public VOrder getVOrderByvSerialNumber(String vSerialNumber){
        return vOrderMapper.getVOrderByvSerialNumber(vSerialNumber);
    }//根据序列化订单号查询订单

    public Integer addVOrder(VOrder vOrder){
        return vOrderMapper.addVOrder(vOrder);
    }//新增订单

    public List<VOrder> getAllVOrderByUserName(Integer no,Integer size,String usernames){
        return vOrderMapper.getAllVOrderByUserName((no-1)*size,size,usernames);
    }//根据名字查询订单

    public Integer allCount(){
        return vOrderMapper.allCount();
    }//查询总记录数

}
