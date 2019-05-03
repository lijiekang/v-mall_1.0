package com.vmall.vseckill.service;

import com.vmall.vutil.exception.StoreNotEnoughException;

public interface OrderService {

    /**
     * 秒杀业务
     * @param userId
     * @param seckillProductId
     * @return
     */
    boolean seckillProduct(Integer userId,Integer seckillProductId) throws StoreNotEnoughException,Exception;


    /**
     * 判断用户是否秒杀到商品
     * @param userId
     * @param productId
     * @return
     */
    boolean isSeckilled(long userId,Integer productId);

    /**
     * 得到秒杀结果
     * @param userId
     * @param productId
     * @return
     */
    long getSeckillResult(long userId,int productId);
}
