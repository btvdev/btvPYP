package com.btvpyp.sampleMatch.service;

import java.util.List;

import com.btvpyp.sampleMatch.model.TabSampleMatch;

public interface TabSampleMatchService {
	
	public List<TabSampleMatch> selectTabSampleMatchs(TabSampleMatch tabSampleMatch);
	
	public Integer insertTabSampleMatch(TabSampleMatch tabSampleMatch);
	
	public Integer updateTabSampleMatch(TabSampleMatch tabSampleMatch);
	
	public Integer deleteTabSampleMatch(TabSampleMatch tabSampleMatch);
	
	public Integer getTotalCount(TabSampleMatch tabSampleMatch);
	
	public TabSampleMatch getOneById(Integer sampleMatchId);
	
	public List<TabSampleMatch> selectManuals(TabSampleMatch tabSampleMatch);
	
	public Integer selectTotalManuals(TabSampleMatch tabSampleMatch);
	
	
}
