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
  private long vSkuId;//sku id

  private String vPhone;//收货人电话
  private VUser vUser;
  private VProduct vProduct;
  private VOrderStatus vOrderStatus;
  private VOrderDetails vOrderDetails;
  private VSku vSku;
  private VProperty vProperty;
  private VProperties vProperties;


  public VProperties getvProperties() {
    return vProperties;
  }

  public void setvProperties(VProperties vProperties) {
    this.vProperties = vProperties;
  }

  public VProperty getvProperty() {
    return vProperty;
  }

  public void setvProperty(VProperty vProperty) {
    this.vProperty = vProperty;
  }

  public long getvSkuId() {
    return vSkuId;
  }

  public void setvSkuId(long vSkuId) {
    this.vSkuId = vSkuId;
  }

  public VSku getvSku() {
    return vSku;
  }

  public void setvSku(VSku vSku) {
    this.vSku = vSku;
  }

  public VOrderDetails getvOrderDetails() {
    return vOrderDetails;
  }

  public void setvOrderDetails(VOrderDetails vOrderDetails) {
    this.vOrderDetails = vOrderDetails;
  }

  public String getvPhone() {
    return vPhone;
  }

  public void setvPhone(String vPhone) {
    this.vPhone = vPhone;
  }

  public void setvProduct(VProduct vProduct) {
    this.vProduct = vProduct;
  }

  public VOrderStatus getvOrderStatus() {
    return vOrderStatus;
  }

  public void setvOrderStatus(VOrderStatus vOrderStatus) {
    this.vOrderStatus = vOrderStatus;
  }

  public VUser getvUser() {
    return vUser;
  }

  public void setvUser(VUser vUser) {
    this.vUser = vUser;
  }

  public VProduct getvProduct() {
    return vProduct;
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

}
