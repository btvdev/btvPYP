package com.btvpyp.dao.gd;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btvpyp.model.gd.TabBroadBackGD;
@Repository
public interface TabBroadBackGDDao {
	
	public List<TabBroadBackGD> selectTabBroadBackGDs(TabBroadBackGD tabBroadBackGD);
	
	public Integer insertTabBroadBackGD(TabBroadBackGD tabBroadBackGD);
}
