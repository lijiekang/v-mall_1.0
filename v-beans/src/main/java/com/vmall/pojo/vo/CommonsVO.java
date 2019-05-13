package com.vmall.pojo.vo;

import com.vmall.pojo.Page;
import com.vmall.pojo.Pagezhang;

import java.util.List;

public class CommonsVO {

    private Pagezhang page;
    private List level1;
    private List commonsList;


    public Pagezhang getPage() {
        return page;
    }

    public void setPage(Pagezhang page) {
        this.page = page;
    }

    public List getLevel1() {
        return level1;
    }

    public void setLevel1(List level1) {
        this.level1 = level1;
    }

    public List getCommonsList() {
        return commonsList;
    }

    public void setCommonsList(List commonsList) {
        this.commonsList = commonsList;
    }
}
