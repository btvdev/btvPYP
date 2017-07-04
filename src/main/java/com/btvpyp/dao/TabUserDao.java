package com.btvpyp.dao;

import org.springframework.stereotype.Repository;

import com.btvpyp.model.TabUser;

@Repository
public interface TabUserDao {
	public TabUser selectUser(TabUser tabUser);
}
