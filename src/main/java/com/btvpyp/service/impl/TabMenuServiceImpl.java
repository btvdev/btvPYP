package com.btvpyp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.dao.TabMenuDao;
import com.btvpyp.model.TabMenu;
import com.btvpyp.service.TabMenuService;
@Service
public class TabMenuServiceImpl implements TabMenuService{
	
	@Autowired
	private TabMenuDao tabMenuDao;

	@Override
	public Integer insertMenu(TabMenu tabMenu) {
		return tabMenuDao.insertMenu(tabMenu);
	}

	@Override
	public Integer updateMenu(TabMenu tabMenu) {
		return tabMenuDao.updateMenu(tabMenu);
	}

	@Override
	public TabMenu getMenuById(Integer menuId) {
		return tabMenuDao.getMenuById(menuId);
	}

	@Override
	public List<TabMenu> getMenus(TabMenu tabMenu) {
		return tabMenuDao.getMenus(tabMenu);
	}

	@Override
	public List<TabMenu> getByIds(List<String> ids) {
		return tabMenuDao.getByIds(ids);
	}

	@Override
	public List<TabMenu> getMenusByLevel(Integer menuLevel) {
		return tabMenuDao.getMenusByLevel(menuLevel);
	}

	@Override
	public Integer deleteMenu(Integer menuId) {
		return tabMenuDao.deleteMenu(menuId);
	}
}
