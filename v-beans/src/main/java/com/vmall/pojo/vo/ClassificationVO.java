package com.vmall.pojo.vo;

import java.util.List;

public class ClassificationVO {
    public int getVtp() {
        return vtp;
    }

    public void setVtp(int vtp) {
        this.vtp = vtp;
    }

    public List getAllLevel() {
        return allLevel;
    }

    public void setAllLevel(List allLevel) {
        this.allLevel = allLevel;
    }

    private int vtp;
    private List allLevel;
}
