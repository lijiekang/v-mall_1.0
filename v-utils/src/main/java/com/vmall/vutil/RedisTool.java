//package com.vmall.vutil;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//public class RedisTool<T> {
//    @Autowired
//    private static RedisTemplate redisTemplate;
//
//
//    /**
//     * 存储值
//     * @param key
//     * @param value
//     */
//    public static void save(Object key,Object value){
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.set(key,value);
//    }
//
//
//    /**
//     * 存储带过期时间的值
//     * @param key
//     * @param second
//     * @param value
//     */
//    public static void saveExpir(Object key,long second,Object value){
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.set(key,value,second);
//    }
//
//
//    public static Object get(Object key){
//        ValueOperations valueOperations=redisTemplate.opsForValue();
//        valueOperations.
//    }
//}
