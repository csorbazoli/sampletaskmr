package com.arvoia.sampletask.model;

public class ErrorResponse {
	
	private String result;
	
	private ErrorInfo errorInfo;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	
	
}
