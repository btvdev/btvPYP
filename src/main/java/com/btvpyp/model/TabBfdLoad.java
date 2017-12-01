package com.btvpyp.model;

import java.io.Serializable;

public class TabBfdLoad implements Serializable{

	private static final long serialVersionUID = 3092305002285376000L;
	private String loaddate;
	private Integer loadflag;  //1.当日已读取数据  0.当日未读取数据
	public String getLoaddate() {
		return loaddate;
	}
	public void setLoaddate(String loaddate) {
		this.loaddate = loaddate;
	}
	public Integer getLoadflag() {
		return loadflag;
	}
	public void setLoadflag(Integer loadflag) {
		this.loadflag = loadflag;
	}
}
