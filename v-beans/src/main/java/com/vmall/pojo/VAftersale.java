package com.vmall.pojo;


public class VAftersale {

  private long vAftersaleId;
  private String vReturnedNum;
  private long vStatus;
  private java.sql.Timestamp vAftersaleDate;
  private long vOrderId;
  private String vReason;
  private String vRemark;
  private String vAccount;
  private double vMoney;
  private long vUserId;
  private VOrder vOrder;

  public VOrder getvOrder() {
    return vOrder;
  }

  public void setvOrder(VOrder vOrder) {
    this.vOrder = vOrder;
  }

  public long getVAftersaleId() {
    return vAftersaleId;
  }

  public void setVAftersaleId(long vAftersaleId) {
    this.vAftersaleId = vAftersaleId;
  }


  public String getVReturnedNum() {
    return vReturnedNum;
  }

  public void setVReturnedNum(String vReturnedNum) {
    this.vReturnedNum = vReturnedNum;
  }


  public long getVStatus() {
    return vStatus;
  }

  public void setVStatus(long vStatus) {
    this.vStatus = vStatus;
  }


  public java.sql.Timestamp getVAftersaleDate() {
    return vAftersaleDate;
  }

  public void setVAftersaleDate(java.sql.Timestamp vAftersaleDate) {
    this.vAftersaleDate = vAftersaleDate;
  }


  public long getVOrderId() {
    return vOrderId;
  }

  public void setVOrderId(long vOrderId) {
    this.vOrderId = vOrderId;
  }


  public String getVReason() {
    return vReason;
  }

  public void setVReason(String vReason) {
    this.vReason = vReason;
  }


  public String getVRemark() {
    return vRemark;
  }

  public void setVRemark(String vRemark) {
    this.vRemark = vRemark;
  }


  public String getVAccount() {
    return vAccount;
  }

  public void setVAccount(String vAccount) {
    this.vAccount = vAccount;
  }


  public double getVMoney() {
    return vMoney;
  }

  public void setVMoney(double vMoney) {
    this.vMoney = vMoney;
  }


  public long getVUserId() {
    return vUserId;
  }

  public void setVUserId(long vUserId) {
    this.vUserId = vUserId;
  }

}
