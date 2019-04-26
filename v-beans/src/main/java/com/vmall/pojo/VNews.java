package com.vmall.pojo;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 新闻类
 */
public class VNews implements Serializable {

  private long vNewsId; //新闻对象
  private String vTitle; //新闻标题
  private String vContent; //新闻内容
  private Timestamp vCreateDate; //新闻创建时间


  public long getvNewsId() {
    return vNewsId;
  }

  public void setvNewsId(long vNewsId) {
    this.vNewsId = vNewsId;
  }

  public String getvTitle() {
    return vTitle;
  }

  public void setvTitle(String vTitle) {
    this.vTitle = vTitle;
  }

  public String getvContent() {
    return vContent;
  }

  public void setvContent(String vContent) {
    this.vContent = vContent;
  }

  public Timestamp getvCreateDate() {
    return vCreateDate;
  }

  public void setvCreateDate(Timestamp vCreateDate) {
    this.vCreateDate = vCreateDate;
  }
}
