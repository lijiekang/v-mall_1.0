package com.vmall.pojo;

import java.io.Serializable;

/**
 * 订单状态类
 */
public class VOrderStatus implements Serializable {

  private long vStatusId;//状态id
  private String vStatusName; //状态名


  public long getvStatusId() {
    return vStatusId;
  }

  public void setvStatusId(long vStatusId) {
    this.vStatusId = vStatusId;
  }

  public String getvStatusName() {
    return vStatusName;
  }

  public void setvStatusName(String vStatusName) {
    this.vStatusName = vStatusName;
  }
}
