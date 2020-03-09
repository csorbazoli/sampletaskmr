package com.arvoia.sampletask.exception;

public class CipherException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	final String message;

	public CipherException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "CipherException :" + this.message;
	}

}
