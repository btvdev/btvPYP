package com.btvpyp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.dao.TabBroadBackDao;
import com.btvpyp.model.TabBroadBack;
import com.btvpyp.service.TabBroadBackService;
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

}
