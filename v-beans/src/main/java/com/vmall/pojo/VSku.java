package com.vmall.pojo;


/**
 * SKU类
 */
public class VSku {

  private long vSkuId;
  private String vSkuName;
  private double vSkuPrice;
  private long vProductId;
  private String vSkuCode;
  private VProduct vProduct;

  public String getVSkuCode() {
    return vSkuCode;
  }

  public void setVSkuCode(String vSkuCode) {
    this.vSkuCode = vSkuCode;
  }

  public VProduct getVProduct() {
    return vProduct;
  }

  public void setVProduct(VProduct vProduct) {
    this.vProduct = vProduct;
  }

  public long getVSkuId() {
    return vSkuId;
  }

  public void setVSkuId(long vSkuId) {
    this.vSkuId = vSkuId;
  }


  public String getVSkuName() {
    return vSkuName;
  }

  public void setVSkuName(String vSkuName) {
    this.vSkuName = vSkuName;
  }


  public double getVSkuPrice() {
    return vSkuPrice;
  }

  public void setVSkuPrice(double vSkuPrice) {
    this.vSkuPrice = vSkuPrice;
  }


  public long getVProductId() {
    return vProductId;
  }

  public void setVProductId(long vProductId) {
    this.vProductId = vProductId;
  }

}
