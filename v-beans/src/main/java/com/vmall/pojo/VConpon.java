package com.vmall.pojo;


public class VConpon {

  private long couponId;
  private String couponName;
  private double couponAccount;
  private double couponNeed;
  private long couponNum;
  private long couponLimit;
  private java.sql.Timestamp couponStart;
  private java.sql.Timestamp couponEnd;


  public long getCouponId() {
    return couponId;
  }

  public void setCouponId(long couponId) {
    this.couponId = couponId;
  }


  public String getCouponName() {
    return couponName;
  }

  public void setCouponName(String couponName) {
    this.couponName = couponName;
  }


  public double getCouponAccount() {
    return couponAccount;
  }

  public void setCouponAccount(double couponAccount) {
    this.couponAccount = couponAccount;
  }


  public double getCouponNeed() {
    return couponNeed;
  }

  public void setCouponNeed(double couponNeed) {
    this.couponNeed = couponNeed;
  }


  public long getCouponNum() {
    return couponNum;
  }

  public void setCouponNum(long couponNum) {
    this.couponNum = couponNum;
  }


  public long getCouponLimit() {
    return couponLimit;
  }

  public void setCouponLimit(long couponLimit) {
    this.couponLimit = couponLimit;
  }


  public java.sql.Timestamp getCouponStart() {
    return couponStart;
  }

  public void setCouponStart(java.sql.Timestamp couponStart) {
    this.couponStart = couponStart;
  }


  public java.sql.Timestamp getCouponEnd() {
    return couponEnd;
  }

  public void setCouponEnd(java.sql.Timestamp couponEnd) {
    this.couponEnd = couponEnd;
  }

}
