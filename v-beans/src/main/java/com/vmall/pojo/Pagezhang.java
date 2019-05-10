package com.vmall.pojo;

public class Pagezhang {
    private int pageno;//当前页数
    private int pageCount;//总页数
    private int recordCount;//记录总数
    private int recordInpage;//每页记录数

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        if(recordCount%10==0){
            pageCount=recordCount/10;
        }else {
            pageCount=recordCount/10+1;
        }
    }

    public int getRecordInpage() {
        return recordInpage;
    }

    public void setRecordInpage(int recordInpage) {
        this.recordInpage = recordInpage;
    }
}
