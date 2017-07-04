package com.btvpyp.service;

import java.util.List;

import com.btvpyp.model.TabSampleMatch;

public interface TabSampleMatchService {
	
	public List<TabSampleMatch> selectTabSampleMatchs(TabSampleMatch tabSampleMatch);
	
	public Integer insertTabSampleMatch(TabSampleMatch tabSampleMatch);
	
	public Integer updateTabSampleMatch(TabSampleMatch tabSampleMatch);
	
	public Integer deleteTabSampleMatch(TabSampleMatch tabSampleMatch);
	
	public Integer getTotalCount(TabSampleMatch tabSampleMatch);
	
	public TabSampleMatch getOneById(Integer sampleMatchId);
	
	
}
