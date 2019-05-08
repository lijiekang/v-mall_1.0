package com.vmall.pojo;


/**
 * 属性选项类
 */
public class VProperties {

  private long vPropertiesId;
  private String vPropertiesName;
  private VProperty vProperty;

  public VProperty getvProperty() {
    return vProperty;
  }

  public void setvProperty(VProperty vProperty) {
    this.vProperty = vProperty;
  }

  public long getvPropertiesId() {
    return vPropertiesId;
  }

  public void setvPropertiesId(long vPropertiesId) {
    this.vPropertiesId = vPropertiesId;
  }

  public String getvPropertiesName() {
    return vPropertiesName;
  }

  public void setvPropertiesName(String vPropertiesName) {
    this.vPropertiesName = vPropertiesName;
  }

}
