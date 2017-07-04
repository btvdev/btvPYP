package com.btvpyp.service;

import java.util.List;

import com.btvpyp.model.TabOperLog;

public interface TabOperLogService {
public List<TabOperLog> selectTabOperLogs(TabOperLog tabOperLog);
	/**
	 * 
	 * @param operLogName   操作行为名称
	 * @param operUserName  操作人
	 * @param objId         对象ID
	 * @return
	 */
	public Integer insertTabOperLog(String operLogName,String operUserName,String objId);
	
	public Integer getTotalCount(TabOperLog tabOperLog);
}
