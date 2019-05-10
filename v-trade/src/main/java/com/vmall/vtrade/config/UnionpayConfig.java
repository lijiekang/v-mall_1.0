package com.vmall.vtrade.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UnionpayConfig {

    /**商户号*/
    public static final String MER_ID = "777290058110048";

    /**
     * 前端异步通知地址
     */
    public static String FRONT_URL = "http://localhost:8080/front.html";

    /**
     * 后台异步通知地址
     */
    public static String BACK_URL = "http://yourdomain/union/notifyurl";
}
