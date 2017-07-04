package com.btvpyp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btvpyp.model.TabBroadBack;

@Repository
public interface TabBroadBackDao {
	public List<TabBroadBack> selectTabBroadBacks(TabBroadBack tabBroadBack);
	
	public Integer insertTabBroadBack(TabBroadBack tabBroadBack);
}
