package com.btvpyp.sampleMatch.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.btvpyp.sample.model.TabSample;
/**
 * 广告样本匹配流水单
 * @author gaobo
 *
 */

public class TabSampleMatch implements Serializable{

	private static final long serialVersionUID = -743749186000302900L;
	private Integer sampleMatchId;
	private String sampleId;
	private String pid;
	private String startTime;
	private String endTime;
	private String createrName;
	private Integer isAdv;
	private String advId;
	private Timestamp createTime;
	private String mainName;
	private Integer length;//样本时长
	private Integer deleteFlg;
	private Integer brandId;
	
	//2017-06-19补充
	private Integer jiNum;        
	private String sampleDate;
	private String tapeNum;       
	private String columnType;
	private String secondName;
	
	//冗余字段
	private Integer startIndex;
	private Integer pageSize;
	private String start;
	private String end;
	private Integer needRed;
	private String lastEndTime;
	
	private TabSample tabSample;
	private String startTimeJsp;
	private String endTimeJsp;
	private String lastDate;
	
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
	public TabSample getTabSample() {
		return tabSample;
	}
	public void setTabSample(TabSample tabSample) {
		this.tabSample = tabSample;
	}
	public Integer getNeedRed() {
		return needRed;
	}
	public void setNeedRed(Integer needRed) {
		this.needRed = needRed;
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
	public String getStartTimeJsp() {
		return startTimeJsp;
	}
	public void setStartTimeJsp(String startTimeJsp) {
		this.startTimeJsp = startTimeJsp;
	}
	public String getEndTimeJsp() {
		return endTimeJsp;
	}
	public void setEndTimeJsp(String endTimeJsp) {
		this.endTimeJsp = endTimeJsp;
	}
	public String getMainName() {
		return mainName;
	}
	public void setMainName(String mainName) {
		this.mainName = mainName;
	}
	public String getLastEndTime() {
		return lastEndTime;
	}
	public void setLastEndTime(String lastEndTime) {
		this.lastEndTime = lastEndTime;
	}
	public Integer getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
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
	public String getAdvId() {
		return advId;
	}
	public void setAdvId(String advId) {
		this.advId = advId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	
}
