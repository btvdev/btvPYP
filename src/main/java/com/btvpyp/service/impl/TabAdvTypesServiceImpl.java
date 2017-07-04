package com.btvpyp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.dao.TabAdvTypesDao;
import com.btvpyp.model.TabAdvTypes;
import com.btvpyp.service.TabAdvTypesService;
/**
 * 广告分类管理服务层实现类
 * @author gaobo
 *
 */
@Service
public class TabAdvTypesServiceImpl implements TabAdvTypesService {
	@Autowired
	private TabAdvTypesDao tabAdvTypesDao;
	
	@Override
	public List<TabAdvTypes> selectAdvTypes(TabAdvTypes tabAdvTypes) {
		return tabAdvTypesDao.selectAdvTypes(tabAdvTypes);
	}

	@Override
	public TabAdvTypes selectObjById(Integer typeId) {
		return tabAdvTypesDao.selectObjById(typeId);
	}

	@Override
	public Integer insertTabAdvTypes(TabAdvTypes tabAdvTypes) {
		return tabAdvTypesDao.insertTabAdvTypes(tabAdvTypes);
	}

	@Override
	public Integer deleteTabAdvTypes(Integer typeId) {
		return tabAdvTypesDao.deleteTabAdvTypes(typeId);
	}

	@Override
	public Integer updateTabAdvTypes(TabAdvTypes tabAdvTypes) {
		return tabAdvTypesDao.updateTabAdvTypes(tabAdvTypes);
	}

	@Override
	public List<TabAdvTypes> selectByLevel(Integer level) {
		return tabAdvTypesDao.selectByLevel(level);
	}

}
