package com.vmall.pojo;


/**
 * 商品属性类
 */
public class VProperty {

  private long vPropertiesId;
  private String vPropertiesValue;
  private String vParentId;

  public long getvPropertiesId() {
    return vPropertiesId;
  }

  public void setvPropertiesId(long vPropertiesId) {
    this.vPropertiesId = vPropertiesId;
  }

  public String getvPropertiesValue() {
    return vPropertiesValue;
  }

  public void setvPropertiesValue(String vPropertiesValue) {
    this.vPropertiesValue = vPropertiesValue;
  }

  public String getvParentId() {
    return vParentId;
  }

  public void setvParentId(String vParentId) {
    this.vParentId = vParentId;
  }
}
