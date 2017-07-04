package com.btvpyp.utils;

public class Page {
	private static Integer pageSize;    //每页几条
	private static Integer pageNum;     //共多少页
	private static Integer totalCount;  //共多少条
	private static Integer startIndex;  //下一页的起始index值
	private static Integer currPage;    //当前页
	
	
	public Page(Integer currPage, Integer pageSize, Integer totalCount){
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalCount=totalCount;
		startIndex = (currPage-1)*pageSize;
		int f = totalCount % pageSize;
		if(f == 0){
			pageNum = totalCount / pageSize;
		}
		if(f > 0){
			pageNum = totalCount / pageSize + 1;
		}
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	
}
