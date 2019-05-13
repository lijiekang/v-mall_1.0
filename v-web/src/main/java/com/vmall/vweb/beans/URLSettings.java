package com.vmall.vweb.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 8:22 2019-05-12
 * @Modifyied By:
 */
@ConfigurationProperties(prefix = "vmall.url")
@Component
public class URLSettings {

    //订单模块路径接口
    private String order_list_url; //订单列表路径
    private String sku_list_url; //sku列表路径
    private String properties_url; //根据spmc查询商品id路径
    private String add_url; //补单路径


    private String seckill_product_url;


    public String getOrder_list_url() {
        return order_list_url;
    }

    public void setOrder_list_url(String order_list_url) {
        this.order_list_url = order_list_url;
    }

    public String getSku_list_url() {
        return sku_list_url;
    }

    public void setSku_list_url(String sku_list_url) {
        this.sku_list_url = sku_list_url;
    }

    public String getProperties_url() {
        return properties_url;
    }

    public void setProperties_url(String properties_url) {
        this.properties_url = properties_url;
    }

    public String getAdd_url() {
        return add_url;
    }

    public void setAdd_url(String add_url) {
        this.add_url = add_url;
    }
}
