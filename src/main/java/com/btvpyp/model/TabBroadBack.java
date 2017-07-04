package com.btvpyp.model;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 播返单实体类
 * @author gaobo
 *
 */
public class TabBroadBack implements Serializable {

	private static final long serialVersionUID = -2877186228538348628L;
	
	private Integer   broadBackId;   //主键
	private String    jmName;		 //节目名称
	private String    starttime;     //开始时间
	private String    endtime;       //结束时间
	private Integer   length;        //时长
	private String    channel;       //频道
	private Timestamp createTime;    //录入时间
	
	//冗余
	private String chooseDay;
	private String chooseDayS;
	private String chooseDayE;
	
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
	public String getChooseDay() {
		return chooseDay;
	}
	public void setChooseDay(String chooseDay) {
		this.chooseDay = chooseDay;
	}
	public String getChooseDayS() {
		return chooseDayS;
	}
	public void setChooseDayS(String chooseDayS) {
		this.chooseDayS = chooseDayS;
	}
	public String getChooseDayE() {
		return chooseDayE;
	}
	public void setChooseDayE(String chooseDayE) {
		this.chooseDayE = chooseDayE;
	}
}