package com.vmall.pojo;


import java.io.Serializable;

/**
 * 品牌类
 */
public class VBrand implements Serializable {

  private long vBrandId;
  private String vBrandName;
  private long vPutaway;


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

  public long getvPutaway() {
    return vPutaway;
  }

  public void setvPutaway(long vPutaway) {
    this.vPutaway = vPutaway;
  }
}
