package com.vmall.mapper.ordermapper;

import com.vmall.pojo.VOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface VOrderMapper {
    VOrder getVOrderByvSerialNumber(String vSerialNumber);//根据序列化订单号查询订单
    Integer addVOrder(VOrder vOrder);//新增订单
    List<VOrder> getAllVOrderByUserName(@Param("no")Integer no,@Param("size")Integer size,@Param("usernames")String usernames);//根据名字查询订单
    Integer allCount();//查询总记录数
}
