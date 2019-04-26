package com.vmall.pojo;


import java.io.Serializable;
import java.util.List;

/**
 * 商品分类对象
 */
public class VCategory implements Serializable {

  private long vCategoryId;  //分类id
  private String vCategoryName; // 分类名
  private long vParentCategoryId; //父级分类id
  private long vType; //分类类型（1-2-3级分类）
  private String vIconClass; //分类图片路径
  private List<VCategory> childrenCategories;  //子分类


  public long getvCategoryId() {
    return vCategoryId;
  }

  public void setvCategoryId(long vCategoryId) {
    this.vCategoryId = vCategoryId;
  }

  public String getvCategoryName() {
    return vCategoryName;
  }

  public void setvCategoryName(String vCategoryName) {
    this.vCategoryName = vCategoryName;
  }

  public long getvParentCategoryId() {
    return vParentCategoryId;
  }

  public void setvParentCategoryId(long vParentCategoryId) {
    this.vParentCategoryId = vParentCategoryId;
  }

  public long getvType() {
    return vType;
  }

  public void setvType(long vType) {
    this.vType = vType;
  }

  public String getvIconClass() {
    return vIconClass;
  }

  public void setvIconClass(String vIconClass) {
    this.vIconClass = vIconClass;
  }

  public List<VCategory> getChildrenCategories() {
    return childrenCategories;
  }

  public void setChildrenCategories(List<VCategory> childrenCategories) {
    this.childrenCategories = childrenCategories;
  }
}
