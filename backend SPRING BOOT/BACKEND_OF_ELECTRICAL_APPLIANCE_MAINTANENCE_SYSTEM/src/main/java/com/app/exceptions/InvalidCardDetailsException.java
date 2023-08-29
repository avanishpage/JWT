package com.app.exceptions;

public class InvalidCardDetailsException extends RuntimeException {
	public InvalidCardDetailsException(String mesg) {
		super(mesg);
	}
}
