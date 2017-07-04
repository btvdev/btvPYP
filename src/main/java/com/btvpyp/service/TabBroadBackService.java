package com.btvpyp.service;

import java.util.List;

import com.btvpyp.model.TabBroadBack;

public interface TabBroadBackService {
	
	public List<TabBroadBack> selectTabBroadBacks(TabBroadBack tabBroadBack);
	
	public Integer insertTabBroadBack(TabBroadBack tabBroadBack);
}
