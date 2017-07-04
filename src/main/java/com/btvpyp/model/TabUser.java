package com.btvpyp.model;

import java.io.Serializable;
/**
 * 用户表
 * @author gaobo
 *
 */
public class TabUser implements Serializable{

	private static final long serialVersionUID = 1092519690118462172L;
	
	private Integer userId;
	private String userName;
	private String password;
	private String realName;
	private Integer status;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}
