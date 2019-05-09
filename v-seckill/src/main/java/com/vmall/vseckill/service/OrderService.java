package com.vmall.vseckill.service;

import com.vmall.pojo.Page;
import com.vmall.pojo.VSeckillOrder;
import com.vmall.vutil.exception.StoreNotEnoughException;

import java.util.List;

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
    List<VSeckillOrder> seckillOrderList(long statusId, long serialNumber, String productName, Page page);
}
