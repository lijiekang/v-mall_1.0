package com.vmall.pojo;

import java.sql.Timestamp;

public class VScore {
    private long v_scoreId;
    private long v_userId;
    private String v_change_score;
    private String v_details;
    private Timestamp v_createDate;
    private int v_type;

    public long getV_scoreId() {
        return v_scoreId;
    }

    public void setV_scoreId(long v_scoreId) {
        this.v_scoreId = v_scoreId;
    }

    public long getV_userId() {
        return v_userId;
    }

    public void setV_userId(long v_userId) {
        this.v_userId = v_userId;
    }

    public String getV_change_score() {
        return v_change_score;
    }

    public void setV_change_score(String v_change_score) {
        this.v_change_score = v_change_score;
    }

    public String getV_details() {
        return v_details;
    }

    public void setV_details(String v_details) {
        this.v_details = v_details;
    }

    public Timestamp getV_createDate() {
        return v_createDate;
    }

    public void setV_createDate(Timestamp v_createDate) {
        this.v_createDate = v_createDate;
    }

    public int getV_type() {
        return v_type;
    }

    public void setV_type(int v_type) {
        this.v_type = v_type;
    }
}
