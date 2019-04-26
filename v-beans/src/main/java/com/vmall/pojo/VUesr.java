package com.vmall.pojo;


import java.io.Serializable;

/**
 * 用户类
 */
public class VUesr implements Serializable {

  private long vUserId; //用户id
  private String vUsername; //用户昵称
  private String vPassword; //用户登录密码
  private String vMail; //用户邮箱
  private String vPhone; //用户手机号
  private String vHeadPath; //用户头像路径
  private long vIsActive; //是否激活
  private long vType; //用户类型（0普通用户 1管理员）
  private double vGrade; //用户积分
  private long vSex; //用户性别（0男 1女）
  private String vIdentity; //用户身份证号
  private String vUsercode; //用户登录名
  private String vSalt;  //用户加密盐值

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

  public String getvPassword() {
    return vPassword;
  }

  public void setvPassword(String vPassword) {
    this.vPassword = vPassword;
  }

  public String getvMail() {
    return vMail;
  }

  public void setvMail(String vMail) {
    this.vMail = vMail;
  }

  public String getvPhone() {
    return vPhone;
  }

  public void setvPhone(String vPhone) {
    this.vPhone = vPhone;
  }

  public String getvHeadPath() {
    return vHeadPath;
  }

  public void setvHeadPath(String vHeadPath) {
    this.vHeadPath = vHeadPath;
  }

  public long getvIsActive() {
    return vIsActive;
  }

  public void setvIsActive(long vIsActive) {
    this.vIsActive = vIsActive;
  }

  public long getvType() {
    return vType;
  }

  public void setvType(long vType) {
    this.vType = vType;
  }

  public double getvGrade() {
    return vGrade;
  }

  public void setvGrade(double vGrade) {
    this.vGrade = vGrade;
  }

  public long getvSex() {
    return vSex;
  }

  public void setvSex(long vSex) {
    this.vSex = vSex;
  }

  public String getvIdentity() {
    return vIdentity;
  }

  public void setvIdentity(String vIdentity) {
    this.vIdentity = vIdentity;
  }

  public String getvUsercode() {
    return vUsercode;
  }

  public void setvUsercode(String vUsercode) {
    this.vUsercode = vUsercode;
  }

  public String getvSalt() {
    return vSalt;
  }

  public void setvSalt(String vSalt) {
    this.vSalt = vSalt;
  }
}
