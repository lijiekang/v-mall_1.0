package com.vmall.vtrade.service.orderservice;

import com.vmall.mapper.ordermapper.VOrderMapper;
import com.vmall.pojo.VOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Integer updateVOrderByorderId(VOrder vOrder){
        return vOrderMapper.updateVOrderByorderId(vOrder);
    }//修改订单(收件人，收货地址,收货人电话)
    public VOrder getVOrderByorderId(Integer vOrderId){
        return vOrderMapper.getVOrderByorderId(vOrderId);
    }//根据订单id获取订单详细信息

    public Integer deleteOrder(Integer vOrderId){
        return vOrderMapper.deleteOrder(vOrderId);
    }//删除订单

    public Integer delCommonsByOrderId(Integer vOrderId){
        return vOrderMapper.delCommonsByOrderId(vOrderId);
    }//删除订单下的评论

    public Integer delAfterSale(Integer vOrderId){
        return vOrderMapper.delAfterSale(vOrderId);
    }//删除订单的售后

    public Integer orderStatus(Integer vOrderId){
        return vOrderMapper.orderStatus(vOrderId);
    }//修改订单是否删除状态

    @Transactional
    public Integer upOrderStatus(){
        //1修改状态
        vOrderMapper.upOrderStatus();
        return 1;
    }//修改订单状态
}
