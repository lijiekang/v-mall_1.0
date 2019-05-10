package com.vmall.pojo;

/**
 * 分页工具类
 * @author 
 *
 */
public class Pages {
	private int pageNo=1;
	private int pageSize=3;
	private int pageye;
	private int pagetiao;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageye() {
		return pageye;
	}
	public void setPageye(int pageye) {
		this.pageye = pageye;
	}
	public int getPagetiao() {
		return pagetiao;
	}
	public void setPagetiao(int pagetiao) {
		this.pagetiao = pagetiao;
		this.pageye=pagetiao%pageSize==0?pagetiao/pageSize:pagetiao/pageSize+1;
	}
}