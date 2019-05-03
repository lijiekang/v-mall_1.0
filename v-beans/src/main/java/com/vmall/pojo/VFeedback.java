package com.vmall.pojo;


/**
 * 反馈表
 */
public class VFeedback {

  private long vFeedbackId;
  private String vFeedbackDescribe;
  private long vFeedbackTypeId;
  private long vFeedbackUserId;


  public long getVFeedbackId() {
    return vFeedbackId;
  }

  public void setVFeedbackId(long vFeedbackId) {
    this.vFeedbackId = vFeedbackId;
  }


  public String getVFeedbackDescribe() {
    return vFeedbackDescribe;
  }

  public void setVFeedbackDescribe(String vFeedbackDescribe) {
    this.vFeedbackDescribe = vFeedbackDescribe;
  }


  public long getVFeedbackTypeId() {
    return vFeedbackTypeId;
  }

  public void setVFeedbackTypeId(long vFeedbackTypeId) {
    this.vFeedbackTypeId = vFeedbackTypeId;
  }


  public long getVFeedbackUserId() {
    return vFeedbackUserId;
  }

  public void setVFeedbackUserId(long vFeedbackUserId) {
    this.vFeedbackUserId = vFeedbackUserId;
  }

}
