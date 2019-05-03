package com.vmall.pojo;


/**
 * 积分变动记录类
 */
public class VScoreChange {

  private long vScoreId;
  private long vUserId;
  private String vChangeScore;
  private String vDetails;
  private java.sql.Timestamp vCreateDate;
  private long vType;


  public long getVScoreId() {
    return vScoreId;
  }

  public void setVScoreId(long vScoreId) {
    this.vScoreId = vScoreId;
  }


  public long getVUserId() {
    return vUserId;
  }

  public void setVUserId(long vUserId) {
    this.vUserId = vUserId;
  }


  public String getVChangeScore() {
    return vChangeScore;
  }

  public void setVChangeScore(String vChangeScore) {
    this.vChangeScore = vChangeScore;
  }


  public String getVDetails() {
    return vDetails;
  }

  public void setVDetails(String vDetails) {
    this.vDetails = vDetails;
  }


  public java.sql.Timestamp getVCreateDate() {
    return vCreateDate;
  }

  public void setVCreateDate(java.sql.Timestamp vCreateDate) {
    this.vCreateDate = vCreateDate;
  }


  public long getVType() {
    return vType;
  }

  public void setVType(long vType) {
    this.vType = vType;
  }

}
