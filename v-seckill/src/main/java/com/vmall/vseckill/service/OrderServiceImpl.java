package com.vmall.vseckill.service;

import com.vmall.mapper.seckill.SeckillOrderMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VSeckillOrder;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.vutil.GenerateNumUtil;
import com.vmall.vutil.exception.StoreNotEnoughException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Autowired
    private ProductService productService;

    private ValueOperations valueOperations;
    public OrderServiceImpl(RedisTemplate redisTemplate) {
        valueOperations=redisTemplate.opsForValue();
    }



    @Transactional
    @Override
    public boolean seckillProduct(Integer userId, Integer seckillProductId) throws StoreNotEnoughException,Exception {
        //减少库存
        if(!productService.modifyProduct(new VSeckillProduct(seckillProductId))){
            //库存不足，则抛出异常
            MQReceiver.flag=true;
            throw new StoreNotEnoughException("库存不足");
        }
        //生成订单号
        String serialNum= GenerateNumUtil.generateOrderNumber(userId,seckillProductId);
        //创建秒杀订单
        VSeckillOrder vSeckillOrder=new VSeckillOrder(seckillProductId,userId,new Timestamp(System.currentTimeMillis()),serialNum);

        //写入秒杀订单
        seckillOrderMapper.insertSeckillOrder(vSeckillOrder);
        //将该条秒杀订单存入redis
        valueOperations.set("seckill_order_"+userId,vSeckillOrder);


        return true;
    }

    @Override
    public boolean isSeckilled(long userId, Integer productId) {
//        return seckillOrderMapper.getSeckillOrderByConditions(userId,productId)!=null?true:false;
        //从redis里获取订单
        return valueOperations.get("seckill_order_"+userId)!=null?true:false;
    }


    @Override
    public long getSeckillResult(long userId, int productId) {
        Object result=valueOperations.get("seckill_order_"+userId);
        if(result!=null)
            return 1;
        else{
            if(!MQReceiver.flag)
                return 0;
            else
                return -1;
        }
    }

    @Override
    public List<VSeckillOrder> seckillOrderList(long statusId, long serialNumber, String productName, Page page) {
        return seckillOrderMapper.seckillOrderList(statusId,serialNumber,productName,(page
        .getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
}
