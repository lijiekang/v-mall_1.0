package com.vmall.pojo;


import java.io.Serializable;

public class VOrderDetails implements Serializable {

  private long vOrderDetailId;
  private long vProductId;
  private long vQuantity;
  private double vCost;
  private VProduct vProduct;

  private long vOrderId;

  public long getvOrderId() {
    return vOrderId;
  }

  public void setvOrderId(long vOrderId) {
    this.vOrderId = vOrderId;
  }

  public long getvOrderDetailId() {
    return vOrderDetailId;
  }

  public void setvOrderDetailId(long vOrderDetailId) {
    this.vOrderDetailId = vOrderDetailId;
  }

  public long getvProductId() {
    return vProductId;
  }

  public void setvProductId(long vProductId) {
    this.vProductId = vProductId;
  }

  public long getvQuantity() {
    return vQuantity;
  }

  public void setvQuantity(long vQuantity) {
    this.vQuantity = vQuantity;
  }

  public double getvCost() {
    return vCost;
  }

  public void setvCost(double vCost) {
    this.vCost = vCost;
  }

  public VProduct getvProduct() {
    return vProduct;
  }

  public void setvProduct(VProduct vProduct) {
    this.vProduct = vProduct;
  }
}
