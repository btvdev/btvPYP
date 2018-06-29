package com.btvpyp.broadBack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.broadBack.dao.TabBroadBackGDDao;
import com.btvpyp.broadBack.model.TabBroadBackGD;
import com.btvpyp.broadBack.service.TabBroadBackGDService;
@Service
public class TabBroadBackGDServiceImpl implements TabBroadBackGDService {
	
	@Autowired
	private TabBroadBackGDDao tabBroadBackGDDao;
	
	@Override
	public List<TabBroadBackGD> selectTabBroadBackGDs(
			TabBroadBackGD tabBroadBackGD) {
		return tabBroadBackGDDao.selectTabBroadBackGDs(tabBroadBackGD);
	}

	@Override
	public Integer insertTabBroadBackGD(TabBroadBackGD tabBroadBackGD) {
		return tabBroadBackGDDao.insertTabBroadBackGD(tabBroadBackGD);
	}

	

}
