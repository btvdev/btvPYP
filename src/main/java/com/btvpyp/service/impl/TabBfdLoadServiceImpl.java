package com.btvpyp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.dao.TabBfdLoadDao;
import com.btvpyp.model.TabBfdLoad;
import com.btvpyp.service.TabBfdLoadService;
@Service
public class TabBfdLoadServiceImpl implements TabBfdLoadService {
	
	@Autowired
	public TabBfdLoadDao tabBfdLoadDao;
	
	@Override
	public Integer insertBfdLoad(TabBfdLoad tabBfdLoad) {
		return tabBfdLoadDao.insertBfdLoad(tabBfdLoad);
	}

	@Override
	public Integer updateBfdLoad(TabBfdLoad tabBfdLoad) {
		return tabBfdLoadDao.updateBfdLoad(tabBfdLoad);
	}

	@Override
	public TabBfdLoad getBfdLoad(String loaddate) {
		return tabBfdLoadDao.getBfdLoad(loaddate);
	}

}
