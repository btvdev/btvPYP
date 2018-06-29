package com.btvpyp.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.system.dao.TabUserDao;
import com.btvpyp.system.model.TabUser;
import com.btvpyp.system.service.TabUserService;
@Service
public class TabUserServiceImpl implements TabUserService {
	@Autowired
	private TabUserDao tabUserDao;
	
	@Override
	public TabUser selectUser(TabUser tabUser) {
		return tabUserDao.selectUser(tabUser);
	}
	
	@Override
	public Integer insertUser(TabUser tabUser) {
		String menus = tabUser.getMenus();
		menus = menus.substring(0, menus.length()-1);
		tabUser.setMenus(menus);
		tabUser.setStatus(1);
		Integer userId = tabUserDao.insertUser(tabUser); 
		return userId;
	}
	
	@Override
	public Integer updateUser(TabUser tabUser) {
		String menus = tabUser.getMenus();
		menus = menus.substring(0, menus.length()-1);
		tabUser.setMenus(menus);
		return tabUserDao.updateUser(tabUser);
	}
	
	@Override
	public Integer deleteUser(Integer userId) {
		return tabUserDao.deleteUser(userId);
	}

	@Override
	public TabUser getUserById(Integer userId) {
		return tabUserDao.getUserById(userId);
	}

	@Override
	public List<TabUser> getUsers(TabUser tabUser) {
		return tabUserDao.getUsers(tabUser);
	}
}
