package com.vmall.vseckill.service;

import com.vmall.mapper.seckill.SeckillOrderMapper;
import com.vmall.pojo.VSeckillOrder;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.vutil.GenerateNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private ProductService productService;



    @Transactional
    @Override
    public boolean SeckillProduct(Integer userId, Integer seckillProductId) {
        //减少库存
        productService.modifyProduct(new VSeckillProduct(seckillProductId));
        //生成订单号
        String serialNum= GenerateNumUtil.generateOrderNumber(userId,seckillProductId);
        //创建秒杀订单
        VSeckillOrder vSeckillOrder=new VSeckillOrder(userId,seckillProductId,new Timestamp(System.currentTimeMillis()),serialNum);

        //写入秒杀订单
        seckillOrderMapper.insertSeckillOrder(vSeckillOrder);
        //将该条秒杀订单存入redis

        return true;
    }

    @Override
    public boolean isSeckilled(long userId, Integer productId) {
        return seckillOrderMapper.getSeckillOrderByConditions(userId,productId)!=null?true:false;
    }
}
