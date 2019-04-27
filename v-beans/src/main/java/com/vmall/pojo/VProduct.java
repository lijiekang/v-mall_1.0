package com.vmall.pojo;


import java.io.Serializable;
import java.util.List;

/**
 * 商品类
 */
public class VProduct implements Serializable {

  private long vProductId; //商品id
  private String vProductName; //商品名
  private long vCategoryLevel1; //商品分类等级1
  private long vCategoryLevel2; //商品分类等级2
  private long vCategoryLevel3;//商品分类等级3
  private long vStore; //商品库存
  private double vPrice; //商品价格
  private String vImgUrl; //商品图片路径
  private double vGrade; //商品积分
  private long vIsDelete; //是否上下架(0上架 1下架)
  private long vBrandId; //品牌id
  private String cateName1;
  private String cateName2;
  private String cateName3;
  private String brandName;

  public String getCateName1() {
    return cateName1;
  }

  public void setCateName1(String cateName1) {
    this.cateName1 = cateName1;
  }

  public String getCateName2() {
    return cateName2;
  }

  public void setCateName2(String cateName2) {
    this.cateName2 = cateName2;
  }

  public String getCateName3() {
    return cateName3;
  }

  public void setCateName3(String cateName3) {
    this.cateName3 = cateName3;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }


  public long getvProductId() {
    return vProductId;
  }

  public void setvProductId(long vProductId) {
    this.vProductId = vProductId;
  }

  public String getvProductName() {
    return vProductName;
  }

  public void setvProductName(String vProductName) {
    this.vProductName = vProductName;
  }

  public long getvCategoryLevel1() {
    return vCategoryLevel1;
  }

  public void setvCategoryLevel1(long vCategoryLevel1) {
    this.vCategoryLevel1 = vCategoryLevel1;
  }

  public long getvCategoryLevel2() {
    return vCategoryLevel2;
  }

  public void setvCategoryLevel2(long vCategoryLevel2) {
    this.vCategoryLevel2 = vCategoryLevel2;
  }

  public long getvCategoryLevel3() {
    return vCategoryLevel3;
  }

  public void setvCategoryLevel3(long vCategoryLevel3) {
    this.vCategoryLevel3 = vCategoryLevel3;
  }

  public long getvStore() {
    return vStore;
  }

  public void setvStore(long vStore) {
    this.vStore = vStore;
  }

  public double getvPrice() {
    return vPrice;
  }

  public void setvPrice(double vPrice) {
    this.vPrice = vPrice;
  }

  public String getvImgUrl() {
    return vImgUrl;
  }

  public void setvImgUrl(String vImgUrl) {
    this.vImgUrl = vImgUrl;
  }

  public double getvGrade() {
    return vGrade;
  }

  public void setvGrade(double vGrade) {
    this.vGrade = vGrade;
  }

  public long getvIsDelete() {
    return vIsDelete;
  }

  public void setvIsDelete(long vIsDelete) {
    this.vIsDelete = vIsDelete;
  }

  public long getvBrandId() {
    return vBrandId;
  }

  public void setvBrandId(long vBrandId) {
    this.vBrandId = vBrandId;
  }
}
