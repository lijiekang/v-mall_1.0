package com.vmall.vseckill;

import com.vmall.mapper.seckill.SeckillProductMapper;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.pojo.VUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;
import java.util.Random;

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

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        ValueOperations valueOperations=stringRedisTemplate.opsForValue();

        valueOperations.set("seckill_product_id","3");
        for (int i=0;i<5;i++){
            long result=valueOperations.decrement("seckill_product_id");
            System.out.println(result);
        }
    }





//    @Test
//    public void test2(){
//        VUesr vUesr=new VUesr();
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("token:111",vUesr);
//
//
//        System.out.println(valueOperations.get("token:111"));
//    }
//
//
//    @Test
//    public void testAddUser(){
//        VUesr vUesr=new VUesr();
//        vUesr.setvUserId(1);
//
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("token",vUesr);
//
//    }

    @Test
    public void getTokens(){
        File file=new File("d:/data.txt");
        if(file.exists())
            file.delete();
        PrintWriter printWriter;
        String token;
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        ValueOperations valueOperations = redisTemplate.opsForValue();

        try {
            printWriter=new PrintWriter(file);
            for (int i=0;i<1000;i++){
                if(i==0) {
                    printWriter.println("productId,token");
                    continue;
                }
                token=generatorToken();
                VUser vUesr=new VUser();
                vUesr.setvUserId(i);
                valueOperations.set(token,vUesr);

                printWriter.println(1+","+token);
            }

            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

//
    Random random=new Random();
    private String generatorToken(){

        return random.nextInt(100000)+"-"+System.currentTimeMillis()+random.nextInt(4);
    }

//
//
//
//    @Test
//    public void test5(){
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        VUesr vUesr=(VUesr) redisTemplate.opsForValue().get("88463-15567736050593");
//        System.out.println(vUesr.getvUserId());
//    }
}
