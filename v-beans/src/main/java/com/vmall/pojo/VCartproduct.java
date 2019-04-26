package com.vmall.pojo;


import java.io.Serializable;

/**
 * 购物车商品对象
 */
public class VCartproduct implements Serializable {

  private long vCartProductId; //购物车商品id
  private long vProductId; //商品id
  private long vNum; //商品数量
  private VProduct vProduct; //商品对象

  public long getvCartProductId() {
    return vCartProductId;
  }

  public void setvCartProductId(long vCartProductId) {
    this.vCartProductId = vCartProductId;
  }

  public long getvProductId() {
    return vProductId;
  }

  public void setvProductId(long vProductId) {
    this.vProductId = vProductId;
  }

  public long getvNum() {
    return vNum;
  }

  public void setvNum(long vNum) {
    this.vNum = vNum;
  }

  public VProduct getvProduct() {
    return vProduct;
  }

  public void setvProduct(VProduct vProduct) {
    this.vProduct = vProduct;
  }
}
