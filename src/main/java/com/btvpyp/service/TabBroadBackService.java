package com.btvpyp.service;

import java.util.List;

import com.btvpyp.model.TabBroadBack;

public interface TabBroadBackService {
	
	public List<TabBroadBack> selectTabBroadBacks(TabBroadBack tabBroadBack);
	
	public List<TabBroadBack> selectForGd(TabBroadBack tabBroadBack);
	
	public Integer insertTabBroadBack(TabBroadBack tabBroadBack);
	
	public Integer batchRemove(List<String> idList);
}
