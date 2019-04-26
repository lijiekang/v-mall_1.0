package com.vmall.pojo;


import java.io.Serializable;
import java.sql.Timestamp;

public class VSeckillProduct implements Serializable {

  private long vSeckillId;
  private long vSeckillProductId;
  private long vSeckillQuantity;
  private double vSeckillPrice;
  private Timestamp vSeckillStartDate;
  private Timestamp vSeckillEndDate;
  private VProduct vProduct;


  public VSeckillProduct(long vSeckillId) {
    this.vSeckillId = vSeckillId;
  }

  public VSeckillProduct() {
  }

  public long getvSeckillId() {
    return vSeckillId;
  }

  public void setvSeckillId(long vSeckillId) {
    this.vSeckillId = vSeckillId;
  }

  public long getvSeckillProductId() {
    return vSeckillProductId;
  }

  public void setvSeckillProductId(long vSeckillProductId) {
    this.vSeckillProductId = vSeckillProductId;
  }

  public long getvSeckillQuantity() {
    return vSeckillQuantity;
  }

  public void setvSeckillQuantity(long vSeckillQuantity) {
    this.vSeckillQuantity = vSeckillQuantity;
  }

  public double getvSeckillPrice() {
    return vSeckillPrice;
  }

  public void setvSeckillPrice(double vSeckillPrice) {
    this.vSeckillPrice = vSeckillPrice;
  }

  public Timestamp getvSeckillStartDate() {
    return vSeckillStartDate;
  }

  public void setvSeckillStartDate(Timestamp vSeckillStartDate) {
    this.vSeckillStartDate = vSeckillStartDate;
  }

  public Timestamp getvSeckillEndDate() {
    return vSeckillEndDate;
  }

  public void setvSeckillEndDate(Timestamp vSeckillEndDate) {
    this.vSeckillEndDate = vSeckillEndDate;
  }

  public VProduct getvProduct() {
    return vProduct;
  }

  public void setvProduct(VProduct vProduct) {
    this.vProduct = vProduct;
  }
}
