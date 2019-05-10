package com.vmall.mapper.ordermapper;

import com.vmall.pojo.VOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface VOrderMapper {
    VOrder getVOrderByvSerialNumber(String vSerialNumber);//根据序列化订单号查询订单
    Integer addVOrder(VOrder vOrder);//新增订单
    List<VOrder> getAllVOrderByUserName(@Param("no")Integer no,@Param("size")Integer size,@Param("usernames")String usernames);//根据名字查询订单
    Integer allCount();//查询总记录数
    Integer updateVOrderByorderId(VOrder vOrder);//修改订单(收件人，收货地址,收货人电话)
    VOrder getVOrderByorderId(Integer vOrderId);//根据订单id获取订单详细信息
    Integer deleteOrder(Integer vOrderId);//删除订单
    Integer delCommonsByOrderId(Integer vOrderId);//删除订单下的评论
    Integer delAfterSale(Integer vOrderId);//删除订单的售后
    Integer orderStatus(Integer vOrderId);//修改订单是否删除状态
    Integer upOrderStatus();//修改订单状态
}
