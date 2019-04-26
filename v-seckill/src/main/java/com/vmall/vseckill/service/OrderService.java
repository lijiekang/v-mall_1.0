package com.vmall.vseckill.service;

import com.vmall.pojo.VSeckillOrder;

public interface OrderService {

    /**
     * 秒杀业务
     * @param userId
     * @param seckillProductId
     * @return
     */
    boolean SeckillProduct(Integer userId,Integer seckillProductId);


    /**
     * 判断用户是否秒杀到商品
     * @param userId
     * @param productId
     * @return
     */
    boolean isSeckilled(long userId,Integer productId);
}
