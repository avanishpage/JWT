package com.app.exceptions;

public class VendorNotFoundException extends RuntimeException {
	
	public VendorNotFoundException(String msg) {
		super(msg);
	}

}