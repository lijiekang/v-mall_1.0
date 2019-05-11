package com.vmall.mapper.orderdetailsmapper;

import com.vmall.pojo.VOrderDetails;

public interface VOrderDetailsMapper {
    Integer addVOrderDetails(VOrderDetails vOrderDetails);
    Integer delOrderDetails(Integer vOrderId);//根据订单id删除订单详情
}
