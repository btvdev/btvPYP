package com.btvpyp.broadBack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.broadBack.dao.TabBroadBackDao;
import com.btvpyp.broadBack.model.TabBroadBack;
import com.btvpyp.broadBack.service.TabBroadBackService;
/**
 * 播返单服务层类
 * @author gaobo
 *
 */
@Service
public class TabBroadBackServiceImpl implements TabBroadBackService {
	
	@Autowired
	private TabBroadBackDao tabBroadBackDao;
	
	@Override
	public List<TabBroadBack> selectTabBroadBacks(TabBroadBack tabBroadBack) {
		return tabBroadBackDao.selectTabBroadBacks(tabBroadBack);
	}

	@Override
	public Integer insertTabBroadBack(TabBroadBack tabBroadBack) {
		tabBroadBack.setCuted(0);
		return tabBroadBackDao.insertTabBroadBack(tabBroadBack);
	}

	@Override
	public Integer batchRemove(List<String> idList) {
		return tabBroadBackDao.batchRemove(idList);
	}

	@Override
	public List<TabBroadBack> selectForGd(TabBroadBack tabBroadBack) {
		return tabBroadBackDao.selectForGd(tabBroadBack);
	}

	@Override
	public List<TabBroadBack> selectUncuted(TabBroadBack tabBroadBack) {
		return tabBroadBackDao.selectUncuted(tabBroadBack);
	}

	@Override
	public Integer updateTabBroadBack(TabBroadBack tabBroadBack) {
		return tabBroadBackDao.updateTabBroadBack(tabBroadBack);
	}

	@Override
	public List<TabBroadBack> selectUndrew(TabBroadBack tabBroadBack) {
		return tabBroadBackDao.selectUndrew(tabBroadBack);
	}

}
