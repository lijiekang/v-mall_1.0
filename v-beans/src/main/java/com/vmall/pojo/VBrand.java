package com.vmall.pojo;


import java.io.Serializable;

/**
 * 品牌类
 */
public class VBrand implements Serializable {

  private long vBrandId;//品牌id
  private String vBrandName;//品牌名称


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
