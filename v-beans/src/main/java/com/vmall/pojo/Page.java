package com.vmall.pojo;

import com.vmall.pojo.VUesr;

import java.util.List;

public class Page {
    private int totalCount;//总条数
    private int currentPageNo;//当前页数
    private int totalPageCount;//总页数
    private int pageSize=3;//页面大小
    private List<VUesr> vUserList;

    public List<VUesr> getvUserList() {
        return vUserList;
    }

    public void setvUserList(List<VUesr> vUserList) {
        this.vUserList = vUserList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPageCount=totalCount%this.pageSize==0?totalCount/this.pageSize:(totalCount/this.pageSize)+1;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
