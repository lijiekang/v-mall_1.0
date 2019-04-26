package com.vmall.pojo;


import com.vmall.pojo.VSeckillProduct;

import java.sql.Timestamp;

public class VSeckillOrder {

  private long vSeckillOrderId;
  private long vSeckillProductId;
  private long vSeckillUserId;
  private java.sql.Timestamp vSeckillCreateDate;
  private String vSeckillSerialNumber;
  private String vSeckillPayNum;
  private VSeckillProduct vSeckillProduct;
  private long vSeckillStatus;

  private VOrderStatus vOrderStatus;

  public VOrderStatus getvOrderStatus() {
    return vOrderStatus;
  }

  public void setvOrderStatus(VOrderStatus vOrderStatus) {
    this.vOrderStatus = vOrderStatus;
  }

  public long getvSeckillStatus() {
    return vSeckillStatus;
  }
  public void setvSeckillStatus(long vSeckillStatus) {
    this.vSeckillStatus = vSeckillStatus;
  }

  public VSeckillOrder(long vSeckillProductId, long vSeckillUserId, Timestamp vSeckillCreateDate, String vSeckillSerialNumber) {
    this.vSeckillProductId = vSeckillProductId;
    this.vSeckillUserId = vSeckillUserId;
    this.vSeckillCreateDate = vSeckillCreateDate;
    this.vSeckillSerialNumber = vSeckillSerialNumber;
  }

  public long getvSeckillOrderId() {
    return vSeckillOrderId;
  }

  public void setvSeckillOrderId(long vSeckillOrderId) {
    this.vSeckillOrderId = vSeckillOrderId;
  }

  public long getvSeckillProductId() {
    return vSeckillProductId;
  }

  public void setvSeckillProductId(long vSeckillProductId) {
    this.vSeckillProductId = vSeckillProductId;
  }

  public long getvSeckillUserId() {
    return vSeckillUserId;
  }

  public void setvSeckillUserId(long vSeckillUserId) {
    this.vSeckillUserId = vSeckillUserId;
  }

  public Timestamp getvSeckillCreateDate() {
    return vSeckillCreateDate;
  }

  public void setvSeckillCreateDate(Timestamp vSeckillCreateDate) {
    this.vSeckillCreateDate = vSeckillCreateDate;
  }

  public String getvSeckillSerialNumber() {
    return vSeckillSerialNumber;
  }

  public void setvSeckillSerialNumber(String vSeckillSerialNumber) {
    this.vSeckillSerialNumber = vSeckillSerialNumber;
  }

  public String getvSeckillPayNum() {
    return vSeckillPayNum;
  }

  public void setvSeckillPayNum(String vSeckillPayNum) {
    this.vSeckillPayNum = vSeckillPayNum;
  }

  public VSeckillProduct getvSeckillProduct() {
    return vSeckillProduct;
  }

  public void setvSeckillProduct(VSeckillProduct vSeckillProduct) {
    this.vSeckillProduct = vSeckillProduct;
  }
}
