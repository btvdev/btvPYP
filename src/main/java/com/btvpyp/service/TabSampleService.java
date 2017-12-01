package com.btvpyp.service;

import java.util.List;

import com.btvpyp.model.TabSample;

public interface TabSampleService {
	
	public List<TabSample> selectTabSamples(TabSample tabSample);
	
	public List<TabSample> selectUnpushTabSamples(TabSample tabSample);
	
	public String insertTabSample(TabSample tabSample);
	
	public String insertTabSample2(TabSample tabSample);
	
	public Integer updateTabSample(TabSample tabSample);
	
	public Integer deleteTabSample(String sampleId);
	
	public Integer getTotalCount(TabSample tabSample);
	
	public Integer getUnpushTotalCount(TabSample tabSample);
	
	public List<TabSample> findByIds(List<String> ids);
	
	public TabSample selectObjById(String sampleId);
	
	public void rePush(String sampleId);
}
