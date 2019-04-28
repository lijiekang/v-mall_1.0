package com.vmall.vseckill.controller;

import com.vmall.pojo.VUesr;
import com.vmall.pojo.dto.Dto;
import com.vmall.pojo.dto.Status;
import com.vmall.vseckill.service.OrderService;
import com.vmall.vseckill.service.ProductService;
import com.vmall.vutil.exception.StoreNotEnoughException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SeckillController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    RedisTemplate redisTemplate;


    private VUesr getUser(String token){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object object=valueOperations.get(token);
        System.out.println(object);
        return object==null?null:(VUesr)object;
    }


    /**
     * 秒杀接口
     * @param token 用户token信息
     * @param productId 商品id
     * @return
     * 错误代码
     * 19132 库存不足
     * 30001 redis连接异常
     */
    @GetMapping(value="/seckill")
    public Dto seckillProduct(String token, Integer productId){
        //获取当前登录的用户
        VUesr user=getUser(token);
        try {
            if(user==null)
                return Dto.failure(Status.AUTH_ERROR);
        } catch (Exception e) {
            return Dto.failure(new Status("30001","服务器内部异常:"+e.getMessage()));

        }


        //判断是否还有库存(使用redis预减库存)
        if(!productService.haveRemain(productId))
            return Dto.failure(Status.NOT_ENOUGH);

        //判断是否秒杀到
        if(orderService.isSeckilled(user.getvUserId(),productId))
            return Dto.failure(Status.REPEAT_SECKILL);

        //调用下单业务
        try {
            orderService.SeckillProduct((int)user.getvUserId(),productId);
        }catch(StoreNotEnoughException e){
            return Dto.failure(new Status("19132",e.getMessage()));
        }catch (Exception e) {
            e.printStackTrace();
            return Dto.failure(new Status("xxx",e.getMessage()));
        }

        return Dto.success(1);
    }


}
