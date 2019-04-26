package com.vmall.pojo;


import java.io.Serializable;

/**
 * 品牌类
 */
public class VBrand implements Serializable {

  private long vBrandId;
  private String vBrandName;


  public long getvBrandId() {
    return vBrandId;
  }

  public void setvBrandId(long vBrandId) {
    this.vBrandId = vBrandId;
  }

  public String getvBrandName() {
    return vBrandName;
  }

  public void setvBrandName(String vBrandName) {
    this.vBrandName = vBrandName;
  }
}
