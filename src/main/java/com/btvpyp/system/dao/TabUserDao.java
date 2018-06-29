package com.btvpyp.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btvpyp.system.model.TabUser;

@Repository
public interface TabUserDao {
	public TabUser selectUser(TabUser tabUser);
	
	public Integer insertUser(TabUser tabUser);
	
	public Integer updateUser(TabUser tabUser);
	
	public Integer deleteUser(Integer userId);
	
	public TabUser getUserById(Integer userId);
	
	public List<TabUser> getUsers(TabUser tabUser);
}
