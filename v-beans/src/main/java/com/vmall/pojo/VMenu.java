package com.vmall.pojo;


import java.util.List;

/**
 * 菜单信息表
 */
public class VMenu {

  private long vMenuId;//菜单id
  private String vUrl;//菜单路径
  private String vRemark;//菜单备注

  //Role
  private List<VRole> roles;

  public List<VRole> getRoles() {
    return roles;
  }

  public void setRoles(List<VRole> roles) {
    this.roles = roles;
  }

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
