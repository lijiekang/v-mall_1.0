package com.vmall.vutil;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    public static String md5(String pwd){
        return DigestUtils.md5Hex(pwd);
    }
    //第一次将明文加密
    public static String oneEncryption(String pwd){
        return md5(pwd);
    }
    //第二次将MD5加密后的值加随机盐加密
    public static String twoEncryption(String pwd,String salt){
        return md5(pwd+salt);
    }
}
