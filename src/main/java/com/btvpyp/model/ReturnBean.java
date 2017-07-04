package com.btvpyp.model;

import java.io.Serializable;

public class ReturnBean implements Serializable{
	private static final long serialVersionUID = -4815449452170022167L;
	private String code;
	private String msg;
	private String obj;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	
	
}
