package com.app.exceptions;

public class CustomerPasswordNotMatchingException extends RuntimeException {
	
	public CustomerPasswordNotMatchingException(String msg) {
		super(msg);
	}

}
