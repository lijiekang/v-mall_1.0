package com.vmall.pojo;


public class VCartproduct {

  private long vCartProductId;
  private long vSkuId;
  private long vNum;
  private VSku vSku;

  public VSku getvSku() {
    return vSku;
  }

  public void setvSku(VSku vSku) {
    this.vSku = vSku;
  }

  public long getVCartProductId() {
    return vCartProductId;
  }

  public void setVCartProductId(long vCartProductId) {
    this.vCartProductId = vCartProductId;
  }


  public long getVSkuId() {
    return vSkuId;
  }

  public void setVSkuId(long vSkuId) {
    this.vSkuId = vSkuId;
  }


  public long getVNum() {
    return vNum;
  }

  public void setVNum(long vNum) {
    this.vNum = vNum;
  }

}
