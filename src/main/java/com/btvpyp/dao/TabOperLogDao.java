package com.btvpyp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.btvpyp.model.TabOperLog;
@Repository
public interface TabOperLogDao {
	
	public List<TabOperLog> selectTabOperLogs(TabOperLog tabOperLog);
	
	public Integer insertTabOperLog(TabOperLog tabOperLog);
	
	public Integer getTotalCount(TabOperLog tabOperLog);
}
