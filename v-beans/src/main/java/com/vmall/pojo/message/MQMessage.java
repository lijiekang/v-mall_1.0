package com.vmall.pojo.message;

import com.vmall.pojo.VUser;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 20:30 2019-04-30
 * @Modifyied By:
 */
public class MQMessage {

    private VUser vUesr;
    private Integer productId;

    public MQMessage(VUser vUesr, Integer productId) {
        this.vUesr = vUesr;
        this.productId = productId;
    }

    public VUser getvUesr() {
        return vUesr;
    }

    public void setvUesr(VUser vUesr) {
        this.vUesr = vUesr;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
