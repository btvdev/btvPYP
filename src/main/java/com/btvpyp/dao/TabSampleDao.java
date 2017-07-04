package com.btvpyp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btvpyp.model.TabSample;

@Repository
public interface TabSampleDao {
	public List<TabSample> selectTabSamples(TabSample tabSample);
	
	public List<TabSample> selectUnpushTabSamples(TabSample tabSample);
	
	public Integer insertTabSample(TabSample tabSample);
	
	public Integer updateTabSample(TabSample tabSample);
	
	public Integer deleteTabSample(String sampleId);
	
	public Integer getTotalCount(TabSample tabSample);
	
	public Integer getUnpushTotalCount(TabSample tabSample);
	
	public List<TabSample> findByIds(List<String> ids);
	
	public TabSample selectObjById(String sampleId);
	
	public TabSample selectObjBySampleCode(String sampleCode);
	
	
}
