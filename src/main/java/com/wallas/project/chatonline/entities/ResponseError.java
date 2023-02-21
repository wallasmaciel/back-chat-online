package com.wallas.project.chatonline.entities;

import java.util.Date;

public class ResponseError {
	private Date timestamp;
	private String status;
	private int statusCode;
	private String error;
	
	public ResponseError() {
		this.timestamp = new Date();
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}  
}
