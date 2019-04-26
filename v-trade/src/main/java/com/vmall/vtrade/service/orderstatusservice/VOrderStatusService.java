package com.vmall.vtrade.service.orderstatusservice;

import com.vmall.mapper.orderstatusmapper.VOrderStatusMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VOrderStatusService {
    @Autowired
    VOrderStatusMapper vOrderStatusMapper;

    public Integer updateVorderStatus(long v_orderId){
        return vOrderStatusMapper.updateVorderStatus(v_orderId);
    }//修改订单状态
}
