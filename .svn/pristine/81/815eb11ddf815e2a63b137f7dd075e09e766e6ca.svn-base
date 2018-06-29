package com.btvpyp.sample.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btvpyp.sample.model.TabSample;

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
	
	public List<TabSample> selectByDimName(String mainName);
	
	public String selectOneNameById(String sampleId);
	
	public List<TabSample> selectForComplete();
	
	public Integer selectMaxBrandId();
}
