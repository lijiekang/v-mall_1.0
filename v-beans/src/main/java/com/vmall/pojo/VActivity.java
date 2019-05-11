package com.vmall.pojo;

/**
 * 商品活动表
 */

public class VActivity {

  private long vActivityId;
  private String vType;
  private long vCategoryId;


  public long getvActivityId() {
    return vActivityId;
  }

  public void setvActivityId(long vActivityId) {
    this.vActivityId = vActivityId;
  }

  public String getVType() {
    return vType;
  }

  public void setVType(String vType) {
    this.vType = vType;
  }


  public long getVCategoryId() {
    return vCategoryId;
  }

  public void setVCategoryId(long vCategoryId) {
    this.vCategoryId = vCategoryId;
  }

}
