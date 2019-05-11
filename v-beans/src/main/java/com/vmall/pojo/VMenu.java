package com.vmall.pojo;


import java.util.List;

/**
 * 菜单信息表
 */
public class VMenu {

  private long vMenuId;
  private String vUrl;
  private String vRemark;

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
