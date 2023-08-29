package com.app.exceptions;

@SuppressWarnings("serial")
public class ServiceNotFoundException extends RuntimeException {
	
	public ServiceNotFoundException(String msg) {
		super(msg);
	}

}