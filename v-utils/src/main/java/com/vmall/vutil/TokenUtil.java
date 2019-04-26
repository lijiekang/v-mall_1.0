//package com.vmall.vutil;
//
//import com.vmall.pojo.VUesr;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//public class TokenUtil {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//
//    public VUesr getVUserByToken(String token){
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        VUesr vUser=(VUesr)valueOperations.get(token);
//        return vUser;
//    }
//}
