package com.vmall.pojo;


public class VCollection {

  private long vCollectionId;
  private long vProductId;
  private long vUserId;
  private VProduct vProduct;

  public VProduct getVProduct() {
    return vProduct;
  }

  public void setVProduct(VProduct vProduct) {
    this.vProduct = vProduct;
  }

  public long getVCollectionId() {
    return vCollectionId;
  }

  public void setVCollectionId(long vCollectionId) {
    this.vCollectionId = vCollectionId;
  }


  public long getVProductId() {
    return vProductId;
  }

  public void setVProductId(long vProductId) {
    this.vProductId = vProductId;
  }


  public long getVUserId() {
    return vUserId;
  }

  public void setVUserId(long vUserId) {
    this.vUserId = vUserId;
  }

}
