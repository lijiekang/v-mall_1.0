package com.vmall.pojo;


/**
 * 菜单信息表
 */
public class VMenu {

  private long vMenuId;//菜单id
  private String vUrl;//菜单路径
  private String vRemark;//菜单备注


  public long getVMenuId() {
    return vMenuId;
  }

  public void setVMenuId(long vMenuId) {
    this.vMenuId = vMenuId;
  }


  public String getVUrl() {
    return vUrl;
  }

  public void setVUrl(String vUrl) {
    this.vUrl = vUrl;
  }


  public String getVRemark() {
    return vRemark;
  }

  public void setVRemark(String vRemark) {
    this.vRemark = vRemark;
  }

}
