package com.btvpyp.service;

import java.util.List;

import com.btvpyp.model.TabAdvTypes;

public interface TabAdvTypesService {
	public List<TabAdvTypes> selectAdvTypes(TabAdvTypes tabAdvTypes);
	public TabAdvTypes selectObjById(Integer typeId);
	public Integer insertTabAdvTypes(TabAdvTypes tabAdvTypes);
	public Integer deleteTabAdvTypes(Integer typeId);
	public Integer updateTabAdvTypes(TabAdvTypes tabAdvTypes);
	public List<TabAdvTypes> selectByLevel(Integer level);
}
