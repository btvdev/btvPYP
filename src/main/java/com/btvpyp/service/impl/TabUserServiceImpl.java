package com.btvpyp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.dao.TabUserDao;
import com.btvpyp.model.TabUser;
import com.btvpyp.service.TabUserService;
@Service
public class TabUserServiceImpl implements TabUserService {
	@Autowired
	private TabUserDao tabUserDao;
	@Override
	public TabUser selectUser(TabUser tabUser) {
		return tabUserDao.selectUser(tabUser);
	}

}
