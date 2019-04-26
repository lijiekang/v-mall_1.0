package com.vmall.vutil;

import org.springframework.util.DigestUtils;

import java.util.Random;

/**
 * 生成多种号码的工具类
 */
public class GenerateNumUtil {

    /**
     * 生成订单号工具
     * @param userId 用户id
     * @param productId 商品id
     * @return
     */
    public static String generateOrderNumber(Integer userId,Integer productId){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(userId);
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append(new Random().nextInt(100000));
        String serialNum= DigestUtils.md5DigestAsHex(stringBuffer.toString().getBytes());
        return serialNum;
    }


}
