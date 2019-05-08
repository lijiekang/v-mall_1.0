package com.vmall.pojo;


import java.sql.Timestamp;

public class VUser{

  private long vUserId; //用户id
  private String vUsername; //用户名
  private String vPassword; //用户密码
  private String vMail; //用户邮箱
  private String vPhone; //用户手机
  private String vHeadPath; //用户头像
  private long vIsActive; //是否激活
  private long vType; //类型
  private double vGrade; //用户积分
  private long vSex; //用户性别 0男 1女
  private String vIdentity; //用户身份证号
  private String vUsercode; //用户编码
  private String vSalt; //加密盐值
  private double vAccount; //用户余额（暂且用不到）
  private java.sql.Timestamp vBirthday; //用户生日
  private String vPaypassword; //支付密码（暂时不需要）
  private String vRealName; //真实姓名
  private String vIDcardFront; //身份证正面照
  private String vIDcardBehind; //背面照
  private java.sql.Timestamp vLastLogin; //最后一次登录时间
  private String vSecurityPass;//安全验证登录密码

  public String getvSecurityPass() {
    return vSecurityPass;
  }

  public void setvSecurityPass(String vSecurityPass) {
    this.vSecurityPass = vSecurityPass;
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

  public double getvAccount() {
    return vAccount;
  }

  public void setvAccount(double vAccount) {
    this.vAccount = vAccount;
  }

  public Timestamp getvBirthday() {
    return vBirthday;
  }

  public void setvBirthday(Timestamp vBirthday) {
    this.vBirthday = vBirthday;
  }

  public String getvPaypassword() {
    return vPaypassword;
  }

  public void setvPaypassword(String vPaypassword) {
    this.vPaypassword = vPaypassword;
  }

  public String getvRealName() {
    return vRealName;
  }

  public void setvRealName(String vRealName) {
    this.vRealName = vRealName;
  }

  public String getvIDcardFront() {
    return vIDcardFront;
  }

  public void setvIDcardFront(String vIDcardFront) {
    this.vIDcardFront = vIDcardFront;
  }

  public String getvIDcardBehind() {
    return vIDcardBehind;
  }

  public void setvIDcardBehind(String vIDcardBehind) {
    this.vIDcardBehind = vIDcardBehind;
  }

  public Timestamp getvLastLogin() {
    return vLastLogin;
  }

  public void setvLastLogin(Timestamp vLastLogin) {
    this.vLastLogin = vLastLogin;
  }
}
