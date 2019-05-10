package com.vmall.pojo.vo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 8:43 2019-05-10
 * @Modifyied By:
 */
public class SeckillProductVO {

    private Integer skuId;
    private Integer skuQuantity;
    private Double skuPrice;
    private Date skuBeginDate;
    private Date skuEndDate;


    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSkuQuantity() {
        return skuQuantity;
    }

    public void setSkuQuantity(Integer skuQuantity) {
        this.skuQuantity = skuQuantity;
    }

    public Double getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Double skuPrice) {
        this.skuPrice = skuPrice;
    }


    public Date getSkuBeginDate() {
        return skuBeginDate;
    }

    public void setSkuBeginDate(Date skuBeginDate) {
        this.skuBeginDate = skuBeginDate;
    }

    public Date getSkuEndDate() {
        return skuEndDate;
    }

    public void setSkuEndDate(Date skuEndDate) {
        this.skuEndDate = skuEndDate;
    }
}
