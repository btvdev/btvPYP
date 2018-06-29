package com.btvpyp.broadBack.service;

import java.util.List;

import com.btvpyp.broadBack.model.TabBroadBack;

public interface TabBroadBackService {
	
	public List<TabBroadBack> selectTabBroadBacks(TabBroadBack tabBroadBack);
	
	public List<TabBroadBack> selectForGd(TabBroadBack tabBroadBack);
	
	public Integer insertTabBroadBack(TabBroadBack tabBroadBack);
	
	public Integer batchRemove(List<String> idList);
	
	public List<TabBroadBack> selectUncuted(TabBroadBack tabBroadBack);
	
	public List<TabBroadBack> selectUndrew(TabBroadBack tabBroadBack);
	
	public Integer updateTabBroadBack(TabBroadBack tabBroadBack);
}
