package com.vmall.vtrade.service.orderdetailsservice;

import com.vmall.mapper.orderdetailsmapper.VOrderDetailsMapper;
import com.vmall.pojo.VOrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VOrderDetailsService {
    @Autowired
    VOrderDetailsMapper vOrderDetailsMapper;
    public Integer addVOrderDetails(VOrderDetails vOrderDetails){
        return vOrderDetailsMapper.addVOrderDetails(vOrderDetails);
    }//新增订单详情

    public Integer delOrderDetails(Integer vOrderId){
        return vOrderDetailsMapper.delOrderDetails(vOrderId);
    }//根据订单id删除订单详情
}
