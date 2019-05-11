package com.vmall.mapper.orderstatusmapper;

import org.apache.ibatis.annotations.Param;

public interface VOrderStatusMapper {
    Integer updateVorderStatus(@Param("v_orderId")long v_orderId,@Param("trade_no")String trade_no);//修改订单状态
}
