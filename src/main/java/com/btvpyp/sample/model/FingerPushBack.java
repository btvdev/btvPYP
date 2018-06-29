package com.btvpyp.sample.model;

import java.io.Serializable;

public class FingerPushBack implements Serializable {
	private static final long serialVersionUID = -6267966249888359434L;
	private String status;
	private String errorMessage;
	private String id;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
