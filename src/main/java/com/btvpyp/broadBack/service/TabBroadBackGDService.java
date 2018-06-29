package com.btvpyp.broadBack.service;

import java.util.List;

import com.btvpyp.broadBack.model.TabBroadBackGD;

public interface TabBroadBackGDService {
	
	public List<TabBroadBackGD> selectTabBroadBackGDs(TabBroadBackGD tabBroadBackGD);
	
	public Integer insertTabBroadBackGD(TabBroadBackGD tabBroadBackGD);
}
