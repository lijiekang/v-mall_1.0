package com.vmall.vutil;




import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;
/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 16:30 2019-05-11
 * @Modifyied By:
 */
public class HttpUtils {

    private static final Integer CONNECT_TIMEOUT = 20000; // 连接超时时间
    private static final Integer SOCKET_TIMEOUT = 20000; // 读取超时时间
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0";

    /**
     * 文件上传
     * @param url 上传url地址
     * @param fileFormName 文件表单名
     * @param file 文件
     * @param params 其他参数
     * @return
     * @throws Exception
     */
    public static String upload(String url, String fileFormName, File file,Map<String,Object> params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig config=RequestConfig.custom()
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();
            httpPost.setConfig(config);
            httpPost.setHeader("User-Agent", USER_AGENT); // 设置浏览器标识
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 兼容性模式
            String fileName = file.getName();
            builder.addBinaryBody(fileFormName, file, ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            // 设置其他参数
            if(null != params){
                for(Entry<String, Object> entry : params.entrySet()){
                    builder.addPart(entry.getKey(), new StringBody(entry.getValue().toString(), ContentType.create("text/plain", Consts.UTF_8)));// 解决参数乱码
                }
            }
            builder.setCharset(Charset.forName("UTF-8"));
            httpPost.setEntity(builder.build());
            response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            int statuCode = response.getStatusLine().getStatusCode();// 响应状态码
            String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            if(statuCode != 200){
                JSONObject jsonObject = JSONObject.parseObject(result);
                throw new Exception(jsonObject.getString("message"));
            }
            return result;
        } finally {
            httpClient.close();
            if(null != response){
                response.close();
            }
        }
    }

    /**
     * 下载文件
     * @param url 请求url
     * @return
     * @throws Exception
     */
    public static byte[] download(String url) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpClient实例
        CloseableHttpResponse response = null;
        try{
            HttpGet httpGet=new HttpGet(url); // 创建httpget实例
            RequestConfig config=RequestConfig.custom()
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();
            httpGet.setConfig(config);
            httpGet.setHeader("User-Agent", USER_AGENT); // 设置浏览器标识
            response=httpClient.execute(httpGet); // 执行http get请求
            HttpEntity entity = response.getEntity(); // 获取返回实体
            int statuCode = response.getStatusLine().getStatusCode();
            if(statuCode == 200){ // 响应状态码
                InputStream in = entity.getContent();
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int r = 0;
                while ((r = in.read(buffer, 0, 1024)) != -1) {
                    baos.write(buffer, 0, r);
                }
                return baos.toByteArray();
            }else{
                String result = EntityUtils.toString(entity, "utf-8");
                JSONObject jsonObject = JSONObject.parseObject(result);
                throw new Exception(jsonObject.getString("message"));
            }
        }finally {
            httpClient.close();
            if(null != response){
                response.close();
            }
        }
    }

    /**
     * get请求
     * @param url 请求url
     * @return
     * @throws Exception
     */
    public static String get(String url) throws Exception{
        CloseableHttpClient httpClient=HttpClients.createDefault(); // 创建httpClient实例
        CloseableHttpResponse response = null;
        try{
            HttpGet httpGet=new HttpGet(url); // 创建httpget实例
            RequestConfig config=RequestConfig.custom()
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();
            httpGet.setConfig(config);
            httpGet.setHeader("User-Agent", USER_AGENT); // 设置浏览器标识
            response=httpClient.execute(httpGet); // 执行http get请求
            HttpEntity entity=response.getEntity(); // 获取返回实体
            String result = EntityUtils.toString(entity, "utf-8");
            int statuCode = response.getStatusLine().getStatusCode();
            if(statuCode != 200){
                JSONObject jsonObject = JSONObject.parseObject(result);
                throw new Exception(jsonObject.getString("message"));
            }
            return result;
        }finally {
            httpClient.close();
            if(null != response){
                response.close();
            }
        }
    }

    /**
     * post请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String,Object> params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig config=RequestConfig.custom()
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();
            httpPost.setConfig(config);
            httpPost.setHeader("User-Agent", USER_AGENT); // 设置浏览器标识
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);// 兼容性模式
            // 设置其他参数
            if(null != params){
                for(Entry<String, Object> entry : params.entrySet()){
                    builder.addPart(entry.getKey(), new StringBody(entry.getValue()==null?"":entry.getValue().toString(), ContentType.create("text/plain", Consts.UTF_8)));// 解决参数乱码
                }
            }
            builder.setCharset(Charset.forName("UTF-8"));
            httpPost.setEntity(builder.build());
            response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            int statuCode = response.getStatusLine().getStatusCode();
            if(statuCode != 200){
                JSONObject jsonObject = JSONObject.parseObject(result);
                throw new Exception(jsonObject.getString("message"));
            }
            return result;
        } finally {
            httpClient.close();
            if(null != response){
                response.close();
            }
        }
    }

    /**
     * post请求
     * @param url
     * @param jsonString json字符串
     * @return
     * @throws Exception
     */
    public static String post(String url,String jsonString) throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig config=RequestConfig.custom()
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .build();
            httpPost.setConfig(config);
            httpPost.setHeader("User-Agent", USER_AGENT); // 设置浏览器标识
            StringEntity s = new StringEntity(jsonString,"utf-8");
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            httpPost.setEntity(s);
            response = httpclient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity());
            int statuCode = response.getStatusLine().getStatusCode();
            if(statuCode != 200){
                JSONObject jsonObject = JSONObject.parseObject(result);
                throw new Exception(jsonObject.getString("message"));
            }
            return result;
        }finally{
            httpclient.close();
            if(null != response){
                response.close();
            }
        }
    }



    public static<T> void writeData(ObjectOutputStream outputStream,T data){
        try {
            outputStream.writeObject(JSONArray.toJSON(data));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
