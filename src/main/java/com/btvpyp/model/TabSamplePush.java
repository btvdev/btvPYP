package com.btvpyp.model;

import java.io.Serializable;

public class TabSamplePush implements Serializable{

	private static final long serialVersionUID = -3848999661816027845L;
	
	private String sampleid;
	private String samplename;
	private String uri;
	private Integer length;
	private Integer category;
	
	public String getSampleid() {
		return sampleid;
	}
	public void setSampleid(String sampleid) {
		this.sampleid = sampleid;
	}
	public String getSamplename() {
		return samplename;
	}
	public void setSamplename(String samplename) {
		this.samplename = samplename;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	
}
