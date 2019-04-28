package com.vmall.vseckill.component;

import com.vmall.pojo.VSeckillProduct;
import com.vmall.vseckill.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SeckillApplicationRunner implements ApplicationRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProductService productService;

    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    @Order(1)
    public void run(ApplicationArguments args) throws Exception {
        List<VSeckillProduct> allProducts=productService.loadSeckillProducts();

        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        for (VSeckillProduct product:
             allProducts) {
            logger.info("----加载秒杀商品："+product.getvProduct().getvProductName()+"\n数量:"+product.getvSeckillQuantity());
            valueOperations.set("seckill_product_"+product.getvProduct().getvProductId(),String.valueOf(product.getvSeckillQuantity()));
            logger.info("秒杀商品key值:seckill_product_"+product.getvProduct().getvProductId());
        }
    }
}
