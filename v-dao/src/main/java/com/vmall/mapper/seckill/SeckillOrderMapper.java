package com.vmall.mapper.seckill;

import com.vmall.pojo.VSeckillOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeckillOrderMapper {

    /**
     * 新增秒杀订单
     * @param vSeckillOrder 秒杀订单
     * @return
     */
    int insertSeckillOrder(VSeckillOrder vSeckillOrder);


    /**
     * 根据用户id和商品id查询秒杀订单
     * @param userId
     * @param productId
     * @return
     */
    VSeckillOrder getSeckillOrderByConditions(@Param("userId")long userId,
                                        @Param("productId")Integer productId);

    /**
     * 全部秒杀订单
     *
     *
     */
    List<VSeckillOrder> seckillOrderList(@Param("statusId")long statusId,
                                         @Param("serialNumber")long serialNumber,
                                         @Param("productName")String productName,
                                         @Param("pageNo")long pageNo,
                                         @Param("pageSize")long pageSize);

    /**
     * 订单列表记录
     * @param statusId
     * @param serialNumber
     * @param productName
     * @return
     */
    int count(@Param("statusId")long statusId,
              @Param("serialNumber")long serialNumber,
              @Param("productName")String productName);
}
