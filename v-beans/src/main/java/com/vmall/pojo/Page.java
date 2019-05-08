package com.vmall.pojo;

import java.util.List;

public class Page {
    private int totalCount;//总条数
    private int currentPageNo;//当前页数
    private int totalPageCount;//总页数
    private int pageSize=1;//页面大小
    private List<VUser> vUserList;

    public List<VUser> getvUserList() {
        return vUserList;
    }

    public void setvUserList(List<VUser> vUserList) {
        this.vUserList = vUserList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount>0){
            this.totalCount = totalCount;
            this.totalPageCount=totalCount%this.pageSize==0?totalCount/this.pageSize:(totalCount/this.pageSize)+1;
        }
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
