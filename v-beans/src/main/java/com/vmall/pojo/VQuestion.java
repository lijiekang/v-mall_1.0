package com.vmall.pojo;


import java.io.Serializable;

/**
 * 用户密保问题类
 */
public class VQuestion implements Serializable {

  private long vQuestionId; //问题id
  private String vQuestionTitle; //问题题目


  public long getvQuestionId() {
    return vQuestionId;
  }

  public void setvQuestionId(long vQuestionId) {
    this.vQuestionId = vQuestionId;
  }

  public String getvQuestionTitle() {
    return vQuestionTitle;
  }

  public void setvQuestionTitle(String vQuestionTitle) {
    this.vQuestionTitle = vQuestionTitle;
  }
}
