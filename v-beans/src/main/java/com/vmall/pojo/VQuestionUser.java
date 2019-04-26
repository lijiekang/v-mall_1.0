package com.vmall.pojo;


import java.io.Serializable;

/**
 * 用户问题关联类
 */
public class VQuestionUser implements Serializable {

  private long vQuId;  //用户问题关联id
  private long vUserId; //用户id
  private long vQuestionId; //问题id
  private String vAnswer; //用户回答


  public long getvQuId() {
    return vQuId;
  }

  public void setvQuId(long vQuId) {
    this.vQuId = vQuId;
  }

  public long getvUserId() {
    return vUserId;
  }

  public void setvUserId(long vUserId) {
    this.vUserId = vUserId;
  }

  public long getvQuestionId() {
    return vQuestionId;
  }

  public void setvQuestionId(long vQuestionId) {
    this.vQuestionId = vQuestionId;
  }

  public String getvAnswer() {
    return vAnswer;
  }

  public void setvAnswer(String vAnswer) {
    this.vAnswer = vAnswer;
  }
}
