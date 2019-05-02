package com.vmall.pojo.message;

import com.vmall.pojo.VUesr;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 20:30 2019-04-30
 * @Modifyied By:
 */
public class MQMessage {

    private VUesr vUesr;
    private Integer productId;

    public VUesr getvUesr() {
        return vUesr;
    }

    public void setvUesr(VUesr vUesr) {
        this.vUesr = vUesr;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public MQMessage(VUesr vUesr, Integer productId) {
        this.vUesr = vUesr;
        this.productId = productId;
    }
}
