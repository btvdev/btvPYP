package com.btvpyp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btvpyp.model.TabSampleMatch;

@Repository
public interface TabSampleMatchDao {
	
	public List<TabSampleMatch> selectTabSampleMatchs(TabSampleMatch tabSampleMatch);
	
	public List<TabSampleMatch> selectTabSampleMatchsExcel(TabSampleMatch tabSampleMatch);
	
	public Integer insertTabSampleMatch(TabSampleMatch tabSampleMatch);
	
	public Integer updateTabSampleMatch(TabSampleMatch tabSampleMatch);
	
	public Integer deleteTabSampleMatch(TabSampleMatch tabSampleMatch);
	
	public Integer getTotalCount(TabSampleMatch tabSampleMatch);
	
	public TabSampleMatch getOneById(Integer sampleMatchId);
}
