package com.app.exceptions;

public class VendorPasswordNotMatchingException extends RuntimeException {

	public VendorPasswordNotMatchingException(String msg) {
		super(msg);
	}
}
