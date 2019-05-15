package com.vmall.pojo;


import java.util.Date;

public class VConpon {

  private long couponId;
  private String couponName;
  private double couponAccount;
  private double couponNeed;
  private long couponNum;
  private long couponLimit;
  private Date couponStart;
  private Date couponEnd;
  private long couponAlready;

  public long getCouponAlready() {
    return couponAlready;
  }

  public void setCouponAlready(long couponAlready) {
    this.couponAlready = couponAlready;
  }

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

  public Date getCouponStart() {
    return couponStart;
  }

  public void setCouponStart(Date couponStart) {
    this.couponStart = couponStart;
  }

  public Date getCouponEnd() {
    return couponEnd;
  }

  public void setCouponEnd(Date couponEnd) {
    this.couponEnd = couponEnd;
  }
}
