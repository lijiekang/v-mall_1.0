package com.vmall.pojo;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户地址类
 */
public class VUserAddress implements Serializable {

  private long vAddressId; //用户地址id
  private long vUserId; //用户id
  private String vAddress; //用户地址
  private Timestamp vCreateDate; //创建时间
  private Timestamp vModifyDate; //修改时间
  private long vIsDefault; //是否默认
  private String vReciveName; //收货人姓名
  private String vRecivePhone; //收货人手机号
  private String vDistributionArea; //描述的地区信息（如：湖南-岳阳-岳阳楼区）
  private String vPostCode; //邮政编码


  public long getvAddressId() {
    return vAddressId;
  }

  public void setvAddressId(long vAddressId) {
    this.vAddressId = vAddressId;
  }

  public long getvUserId() {
    return vUserId;
  }

  public void setvUserId(long vUserId) {
    this.vUserId = vUserId;
  }

  public String getvAddress() {
    return vAddress;
  }

  public void setvAddress(String vAddress) {
    this.vAddress = vAddress;
  }

  public Timestamp getvCreateDate() {
    return vCreateDate;
  }

  public void setvCreateDate(Timestamp vCreateDate) {
    this.vCreateDate = vCreateDate;
  }

  public Timestamp getvModifyDate() {
    return vModifyDate;
  }

  public void setvModifyDate(Timestamp vModifyDate) {
    this.vModifyDate = vModifyDate;
  }

  public long getvIsDefault() {
    return vIsDefault;
  }

  public void setvIsDefault(long vIsDefault) {
    this.vIsDefault = vIsDefault;
  }

  public String getvReciveName() {
    return vReciveName;
  }

  public void setvReciveName(String vReciveName) {
    this.vReciveName = vReciveName;
  }

  public String getvRecivePhone() {
    return vRecivePhone;
  }

  public void setvRecivePhone(String vRecivePhone) {
    this.vRecivePhone = vRecivePhone;
  }

  public String getvDistributionArea() {
    return vDistributionArea;
  }

  public void setvDistributionArea(String vDistributionArea) {
    this.vDistributionArea = vDistributionArea;
  }

  public String getvPostCode() {
    return vPostCode;
  }

  public void setvPostCode(String vPostCode) {
    this.vPostCode = vPostCode;
  }
}
