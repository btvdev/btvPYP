package com.btvpyp.model.gd;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 播返单数据归档实体类
 * @author gaobo
 *
 */
public class TabBroadBackGD implements Serializable {

	private static final long serialVersionUID = -3506986046404842086L;
	
	private Integer   broadBackId;   //主键
	private String    jmName;		 //节目名称
	private String    starttime;     //开始时间
	private String    endtime;       //结束时间
	private Integer   length;        //时长
	private String    channel;       //频道
	private Timestamp createTime;    //录入时间
	
	public Integer getBroadBackId() {
		return broadBackId;
	}
	public void setBroadBackId(Integer broadBackId) {
		this.broadBackId = broadBackId;
	}
	public String getJmName() {
		return jmName;
	}
	public void setJmName(String jmName) {
		this.jmName = jmName;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
