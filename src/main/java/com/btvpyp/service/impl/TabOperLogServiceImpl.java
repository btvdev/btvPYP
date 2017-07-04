package com.btvpyp.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.dao.TabOperLogDao;
import com.btvpyp.model.TabOperLog;
import com.btvpyp.service.TabOperLogService;
/**
 * 操作日志管理服务层实现类
 * @author gaobo
 *
 */
@Service
public class TabOperLogServiceImpl implements TabOperLogService {
	
	@Autowired
	public TabOperLogDao tabOperLogDao;
	
	@Override
	public List<TabOperLog> selectTabOperLogs(TabOperLog tabOperLog) {
		return tabOperLogDao.selectTabOperLogs(tabOperLog);
	}

	@Override
	public Integer insertTabOperLog(String operLogName,String operUserName,String objId) {
		TabOperLog tabOperLog = new TabOperLog();
		tabOperLog.setOperLogName(operLogName);
		tabOperLog.setOperUserName(operUserName);
		tabOperLog.setObjId(objId);
		tabOperLog.setOperLogTime(new Timestamp(System.currentTimeMillis()));
		return tabOperLogDao.insertTabOperLog(tabOperLog);
	}

	@Override
	public Integer getTotalCount(TabOperLog tabOperLog) {
		return tabOperLogDao.getTotalCount(tabOperLog);
	}
}
