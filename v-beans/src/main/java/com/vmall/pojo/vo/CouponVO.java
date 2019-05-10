package com.vmall.pojo.vo;

import java.util.Date;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 10:07 2019-05-10
 * @Modifyied By:
 */
public class CouponVO {

    private String couponName;
    private double couponAccount;
    private double couponNeed;
    private Integer couponNum;
    private Integer couponLimit;
    private Date couponStart;
    private Date couponEnd;


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

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public Integer getCouponLimit() {
        return couponLimit;
    }

    public void setCouponLimit(Integer couponLimit) {
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
