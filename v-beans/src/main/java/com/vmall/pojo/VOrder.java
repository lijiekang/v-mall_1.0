package com.vmall.pojo;


import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 订单类
 */
public class VOrder implements Serializable {

  private long vOrderId; //订单id
  private long vUserId; //用户id
  private String vUsername; //用户名
  private String vUserAddress; //用户地址
  private Timestamp vCreateTime; //创建时间
  private double vCost; //订单花费
  private String vSerialNumber; //序列化的订单号
  private long vStatusId; //订单状态
  private String vPayNum; //交易号
  private long vOrderDetailId; //订单详情
  private long vProductId;//商品id

  private VUesr vUesr;
  private VProduct vProduct;
  private VOrderStatus vOrderStatus;

  public void setvProduct(VProduct vProduct) {
    this.vProduct = vProduct;
  }

  public VOrderStatus getvOrderStatus() {
    return vOrderStatus;
  }

  public void setvOrderStatus(VOrderStatus vOrderStatus) {
    this.vOrderStatus = vOrderStatus;
  }

  public VUesr getvUesr() {
    return vUesr;
  }

  public void setvUesr(VUesr vUesr) {
    this.vUesr = vUesr;
  }

  public VProduct getvProduct() {
    return vProduct;
  }

  public long getvProductId() {
    return vProductId;
  }

  public void setvProductId(long vProductId) {
    this.vProductId = vProductId;
  }

  public long getvOrderId() {
    return vOrderId;
  }

  public void setvOrderId(long vOrderId) {
    this.vOrderId = vOrderId;
  }

  public long getvUserId() {
    return vUserId;
  }

  public void setvUserId(long vUserId) {
    this.vUserId = vUserId;
  }

  public String getvUsername() {
    return vUsername;
  }

  public void setvUsername(String vUsername) {
    this.vUsername = vUsername;
  }

  public String getvUserAddress() {
    return vUserAddress;
  }

  public void setvUserAddress(String vUserAddress) {
    this.vUserAddress = vUserAddress;
  }

  public Timestamp getvCreateTime() {
    return vCreateTime;
  }

  public void setvCreateTime(Timestamp vCreateTime) {
    this.vCreateTime = vCreateTime;
  }

  public double getvCost() {
    return vCost;
  }

  public void setvCost(double vCost) {
    this.vCost = vCost;
  }

  public String getvSerialNumber() {
    return vSerialNumber;
  }

  public void setvSerialNumber(String vSerialNumber) {
    this.vSerialNumber = vSerialNumber;
  }

  public long getvStatusId() {
    return vStatusId;
  }

  public void setvStatusId(long vStatusId) {
    this.vStatusId = vStatusId;
  }

  public String getvPayNum() {
    return vPayNum;
  }

  public void setvPayNum(String vPayNum) {
    this.vPayNum = vPayNum;
  }

  public long getvOrderDetailId() {
    return vOrderDetailId;
  }

  public void setvOrderDetailId(long vOrderDetailId) {
    this.vOrderDetailId = vOrderDetailId;
  }
}
