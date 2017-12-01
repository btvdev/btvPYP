package com.btvpyp.dao;

import org.springframework.stereotype.Repository;

import com.btvpyp.model.TabBfdLoad;

@Repository
public interface TabBfdLoadDao {
	public Integer insertBfdLoad(TabBfdLoad tabBfdLoad);
	public Integer updateBfdLoad(TabBfdLoad tabBfdLoad);
	public TabBfdLoad getBfdLoad(String loaddate);
}
