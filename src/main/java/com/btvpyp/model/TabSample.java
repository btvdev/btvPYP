package com.btvpyp.model;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 广告样本库表
 * @author gaobo
 *
 */
public class TabSample implements Serializable{

	private static final long serialVersionUID = 3817433309112909204L;
	
	private String sampleId;      //主键，即样本代码
	private Integer flag;         //标志
	private String sampleCode;    //节目代码   (暂时弃用的字段)
	private String startTime;     //开始时间(时间戳)
	private String endTime;		  //结束时间(时间戳)
	private Integer jiNum;        //集数
	private Integer length;       //长度
	private String location;      //播出位置
	private String comments;      //备注
	private String sampleDate;    //日期
	private String pid;           //频道ID
	private String mainName;      //主名称
	private String secondName;    //副名称
	private String tapeNum;       //磁带号
	private String columnType;    //栏目类别
	private String advId;         //广告ID
	private String createrName;   //录入人
	private Timestamp createTime; //录入日期
	private String lastModifier;  //最后修改人
	private Timestamp lastModifyTime;//最后修改时间
	private String company;       //代理公司
	private String fileAddr;      //上传文件的地址
	private Integer extFlag;      //提取标志
	private String largeType;     //广告分类(大)
	private String middleType;    //广告分类(中)
	private String tinyType;      //广告分类(小)
	private Integer isAdv;        //是否为广告(传文件的为广告样本，否则反之)
	private String fileNetAddr;   //文件外网链接地址
	               
	
	//冗余字段
	private Integer startIndex;
	private Integer pageSize;
	private String start;
	private String end;
	private String saveJs;
	
	public String getSampleId() {
		return sampleId;
	}
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	public Integer getJiNum() {
		return jiNum;
	}
	public void setJiNum(Integer jiNum) {
		this.jiNum = jiNum;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSampleDate() {
		return sampleDate;
	}
	public void setSampleDate(String sampleDate) {
		this.sampleDate = sampleDate;
	}
	public String getMainName() {
		return mainName;
	}
	public void setMainName(String mainName) {
		this.mainName = mainName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
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
	public String getAdvId() {
		return advId;
	}
	public void setAdvId(String advId) {
		this.advId = advId;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getLastModifier() {
		return lastModifier;
	}
	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}
	public Timestamp getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getFileAddr() {
		return fileAddr;
	}
	public void setFileAddr(String fileAddr) {
		this.fileAddr = fileAddr;
	}
	public Integer getExtFlag() {
		return extFlag;
	}
	public void setExtFlag(Integer extFlag) {
		this.extFlag = extFlag;
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
	public String getLargeType() {
		return largeType;
	}
	public void setLargeType(String largeType) {
		this.largeType = largeType;
	}
	public String getMiddleType() {
		return middleType;
	}
	public void setMiddleType(String middleType) {
		this.middleType = middleType;
	}
	public String getTinyType() {
		return tinyType;
	}
	public void setTinyType(String tinyType) {
		this.tinyType = tinyType;
	}
	public Integer getIsAdv() {
		return isAdv;
	}
	public void setIsAdv(Integer isAdv) {
		this.isAdv = isAdv;
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
	public String getFileNetAddr() {
		return fileNetAddr;
	}
	public void setFileNetAddr(String fileNetAddr) {
		this.fileNetAddr = fileNetAddr;
	}
	public String getSaveJs() {
		return saveJs;
	}
	public void setSaveJs(String saveJs) {
		this.saveJs = saveJs;
	}
	
}
