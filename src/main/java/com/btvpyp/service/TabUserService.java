package com.btvpyp.service;

import java.util.List;

import com.btvpyp.model.TabUser;

public interface TabUserService {
	public TabUser selectUser(TabUser tabUser);
	
	public Integer insertUser(TabUser tabUser);
	
	public Integer updateUser(TabUser tabUser);
	
	public Integer deleteUser(Integer userId);
	
	public TabUser getUserById(Integer userId);
	
	public List<TabUser> getUsers(TabUser tabUser);
}
