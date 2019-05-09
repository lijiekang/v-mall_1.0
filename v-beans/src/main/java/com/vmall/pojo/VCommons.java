package com.vmall.pojo;


import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 评论对象
 */
public class VCommons implements Serializable {

  private long vCommonsId; //评论id
  private long vProductId; //商品id
  private String vContent; //评论内容
  private long vOrderId; //订单id
  private long vUserId; //用户id
  private double vGrade; //评分
  private Timestamp vCreateDate; //评论创建时间
  private Timestamp vModifyDate; //评论修改时间
  private long vIsOk; //是否推荐
  private VProduct vProduct;

  public VProduct getvProduct() {
    return vProduct;
  }

  public void setvProduct(VProduct vProduct) {
    this.vProduct = vProduct;
  }

  public long getvCommonsId() {
    return vCommonsId;
  }

  public void setvCommonsId(long vCommonsId) {
    this.vCommonsId = vCommonsId;
  }

  public long getvProductId() {
    return vProductId;
  }

  public void setvProductId(long vProductId) {
    this.vProductId = vProductId;
  }

  public String getvContent() {
    return vContent;
  }

  public void setvContent(String vContent) {
    this.vContent = vContent;
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

  public double getvGrade() {
    return vGrade;
  }

  public void setvGrade(double vGrade) {
    this.vGrade = vGrade;
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

  public long getvIsOk() {
    return vIsOk;
  }

  public void setvIsOk(long vIsOk) {
    this.vIsOk = vIsOk;
  }
}
