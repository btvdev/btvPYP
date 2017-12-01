package com.btvpyp.model.gd;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 广告样本匹配流水单
 * @author gaobo
 *
 */

public class TabSampleMatchGD implements Serializable{

	private static final long serialVersionUID = -743749186000302900L;
	private Integer sampleMatchId;
	private String sampleId;
	private String pid;
	private String startTime;
	private String endTime;
	private String createrName;
	private Integer isAdv;
	private Timestamp createTime;
	private String mainName;
	private Integer length;//样本时长
	private Integer deleteFlg;
	
	private Integer jiNum;        
	private String sampleDate;
	private String tapeNum;       
	private String columnType;
	private String secondName;
	
	public Integer getSampleMatchId() {
		return sampleMatchId;
	}
	public void setSampleMatchId(Integer sampleMatchId) {
		this.sampleMatchId = sampleMatchId;
	}
	public String getSampleId() {
		return sampleId;
	}
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public Integer getIsAdv() {
		return isAdv;
	}
	public void setIsAdv(Integer isAdv) {
		this.isAdv = isAdv;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getMainName() {
		return mainName;
	}
	public void setMainName(String mainName) {
		this.mainName = mainName;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public Integer getJiNum() {
		return jiNum;
	}
	public void setJiNum(Integer jiNum) {
		this.jiNum = jiNum;
	}
	public String getSampleDate() {
		return sampleDate;
	}
	public void setSampleDate(String sampleDate) {
		this.sampleDate = sampleDate;
	}
	public String getTapeNum() {
		return tapeNum;
	}
	public void setTapeNum(String tapeNum) {
		this.tapeNum = tapeNum;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
}
