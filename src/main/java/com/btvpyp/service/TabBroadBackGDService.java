package com.btvpyp.service;

import java.util.List;

import com.btvpyp.model.gd.TabBroadBackGD;

public interface TabBroadBackGDService {
	
	public List<TabBroadBackGD> selectTabBroadBackGDs(TabBroadBackGD tabBroadBackGD);
	
	public Integer insertTabBroadBackGD(TabBroadBackGD tabBroadBackGD);
}
