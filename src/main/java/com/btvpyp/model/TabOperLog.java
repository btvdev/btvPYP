package com.btvpyp.model;

import java.io.Serializable;
import java.sql.Timestamp;
/*
 * 操作日志实体类
 */
public class TabOperLog implements Serializable{

	private static final long serialVersionUID = -1475788361083041690L;
	
	private Integer operLogId;      //主键
	private String operLogName;		//操作名称
	private String operUserName;	//操作人
	private Timestamp operLogTime;  //操作时间
	private String objId;           //操作对象ID
	
	//冗余字段
	private Integer startIndex;
	private Integer pageSize;
	private String start;
	private String end;
	
	public Integer getOperLogId() {
		return operLogId;
	}
	public void setOperLogId(Integer operLogId) {
		this.operLogId = operLogId;
	}
	public String getOperLogName() {
		return operLogName;
	}
	public void setOperLogName(String operLogName) {
		this.operLogName = operLogName;
	}
	public String getOperUserName() {
		return operUserName;
	}
	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}
	public Timestamp getOperLogTime() {
		return operLogTime;
	}
	public void setOperLogTime(Timestamp operLogTime) {
		this.operLogTime = operLogTime;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
}
