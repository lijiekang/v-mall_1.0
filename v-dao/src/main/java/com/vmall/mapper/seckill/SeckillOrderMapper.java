package com.vmall.mapper.seckill;

import com.vmall.pojo.VSeckillOrder;
import org.apache.ibatis.annotations.Param;

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




}
