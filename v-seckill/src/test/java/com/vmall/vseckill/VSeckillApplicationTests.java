package com.vmall.vseckill;

import com.vmall.mapper.seckill.SeckillProductMapper;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.pojo.VUesr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VSeckillApplicationTests {

    @Autowired
    SeckillProductMapper seckillProductMapper;

    @Test
    public void contextLoads() {
        List<VSeckillProduct> vSeckillProducts = seckillProductMapper.listSeckillProducts();
        for (VSeckillProduct product :
                vSeckillProducts) {
            System.out.println(product.getvSeckillId());
        }
    }


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test(){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        valueOperations.set("nihao","wohenhao");
        System.out.println(valueOperations.get("nihao"));
    }





    @Test
    public void test2(){
        VUesr vUesr=new VUesr();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("token:111",vUesr);


        System.out.println(valueOperations.get("token:111"));
    }


    @Test
    public void testAddUser(){
        VUesr vUesr=new VUesr();
        vUesr.setvUserId(1);

        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("token",vUesr);

    }
}
