package com.btvpyp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btvpyp.model.TabAdvTypes;

@Repository
public interface TabAdvTypesDao {
	public List<TabAdvTypes> selectAdvTypes(TabAdvTypes tabAdvTypes);
	public TabAdvTypes selectObjById(Integer typeId);
	public Integer insertTabAdvTypes(TabAdvTypes tabAdvTypes);
	public Integer deleteTabAdvTypes(Integer typeId);
	public Integer updateTabAdvTypes(TabAdvTypes tabAdvTypes);
	public List<TabAdvTypes> selectByLevel(Integer level);
}
