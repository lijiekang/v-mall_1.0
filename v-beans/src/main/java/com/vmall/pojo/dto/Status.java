package com.vmall.pojo.dto;

public class Status {

    private String errorCode;
    private String message;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Status(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    //成功返回信息
    public static final Status SUCCESS=new Status("0","成功");
    //登录错误状态信息
    public static final Status AUTH_ERROR=new Status("10001","用户名或者密码错误");
    //商品数量不足
    public static final Status NOT_ENOUGH=new Status("20001","商品已被抢光了");
    //重复秒杀
    public static final Status REPEAT_SECKILL=new Status("20002","不能重复秒杀");


}
