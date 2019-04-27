package com.vmall.vseckill.service;

import com.vmall.mapper.seckill.SeckillProductMapper;
import com.vmall.pojo.VSeckillProduct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private SeckillProductMapper seckillProductMapper;


    ValueOperations valueOperations;

    public ProductServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.valueOperations=stringRedisTemplate.opsForValue();
    }


    @Override
    public boolean addSeckillProduct(VSeckillProduct vSeckillProduct) {
        return seckillProductMapper.insertSeckillProduct(vSeckillProduct)==1?true:false;
    }

    @Override
    public List<VSeckillProduct> loadSeckillProducts() {
        return seckillProductMapper.listSeckillProducts();
    }

    @Override
    public boolean modifyProduct(VSeckillProduct vSeckillProduct) {
        return seckillProductMapper.updateSeckillProduct(vSeckillProduct)==1?true:false;
    }

    @Override
    public boolean haveRemain(Integer seckillProductId) {

        //修改，从redis获取,预减库存
        long result= valueOperations.decrement("seckill_product_"+seckillProductId);
        return result>=0;
        //老版本：从数据库获取
        //return seckillProductMapper.getSeckillProductRemain(seckillProductId)>0;
    }

    @Override
    public long getProductQuantityByProductId(Integer productId) {
        List<VSeckillProduct> products=seckillProductMapper.listSeckillProducts();
        for (VSeckillProduct product :
                products) {
            if (product.getvSeckillProductId() == productId)
                return product.getvSeckillQuantity();
        }
        return -1;
    }
}
