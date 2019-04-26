package com.vmall.vutil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 发送验证码工具类
 */
public class SMSCode {

    /**
     * 发送验证码
     * @param phoneNumber 发送的手机号
     * @param code 发送的验证码
     * @return
     */
    public static boolean sendCode(String phoneNumber,String code) throws Exception{
        String code_str= URLEncoder.encode("#code#="+code,"utf-8");
        System.out.println("code:"+code);
        System.out.println("code_str:"+code_str);
        //包装好URL对象，将接口地址包装到此对象中
        URL url=new URL("http://v.juhe.cn/sms/send?mobile="+phoneNumber+"&tpl_id=134398&tpl_value="+code_str+"&key=fde9bae87f3058ec0439a9c6ee781bb9");
        System.out.println(url.toString());
        //打开连接获取连接对象
        URLConnection connection=url.openConnection();
        //向服务器发送连接请求
        connection.connect();

        //获取服务器相应的数据
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

        StringBuffer buffer=new StringBuffer();
        String lineDate=null;
        while((lineDate=bufferedReader.readLine())!=null){
            buffer.append(lineDate);
        }
        bufferedReader.close();
        System.out.println("结果："+buffer.toString());
        if(buffer.toString().indexOf("\"error_code\":0")>0){
            return true;
        }
        return false;

    }

//    public static void main(String[] args) throws Exception{
//        sendCode("17573057935","123123");
//    }
}
