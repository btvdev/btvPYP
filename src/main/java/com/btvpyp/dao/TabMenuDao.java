package com.btvpyp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btvpyp.model.TabMenu;

@Repository
public interface TabMenuDao {
	public Integer insertMenu(TabMenu tabMenu);
	
	public Integer updateMenu(TabMenu tabMenu);
	
	public TabMenu getMenuById(Integer menuId);
	
	public List<TabMenu> getMenus(TabMenu tabMenu);
	
	public List<TabMenu> getByIds(List<String> ids);
	
	public List<TabMenu> getMenusByLevel(Integer menuLevel);
	
	public Integer deleteMenu(Integer menuId);
}
