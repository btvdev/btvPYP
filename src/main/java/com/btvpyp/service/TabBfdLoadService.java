package com.btvpyp.service;

import com.btvpyp.model.TabBfdLoad;

public interface TabBfdLoadService {
	public Integer insertBfdLoad(TabBfdLoad tabBfdLoad);
	public Integer updateBfdLoad(TabBfdLoad tabBfdLoad);
	public TabBfdLoad getBfdLoad(String loaddate);
}
