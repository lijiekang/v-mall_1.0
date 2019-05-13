package com.vmall.vtrade.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@ConfigurationProperties(prefix = "pay.alipay")
@Component
public class AlipayConfig {
    //appid
    public String appid;
    //网关
    public String gatewayUrl;
    //应用私钥
    public String appPrivateKey;
    //应用公钥
    public String alipayPublicKey;
    //支付宝公钥
    public String zhifuPublicKey;
    //同步地址
    public String returnUrl;
    //异步地址
    public String notifyUrl;
    //签名方式
    public String sign_type;
    //字符编码格式
    public String charset;
    // 返回格式
    public String FORMAT = "json";
    //日志
    public static String log_path="E:\\电商项目\\支付宝支付\\日志";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord){
        FileWriter writer=null;
        try {
            writer=new FileWriter(log_path+"alipay_log_"+System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getZhifuPublicKey() {
        return zhifuPublicKey;
    }

    public void setZhifuPublicKey(String zhifuPublicKey) {
        this.zhifuPublicKey = zhifuPublicKey;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getFORMAT() {
        return FORMAT;
    }

    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    public static String getLog_path() {
        return log_path;
    }

    public static void setLog_path(String log_path) {
        AlipayConfig.log_path = log_path;
    }
}
