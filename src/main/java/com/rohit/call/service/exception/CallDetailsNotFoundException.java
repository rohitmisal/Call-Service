package com.rohit.call.service.exception;

public class CallDetailsNotFoundException extends RuntimeException {

	public CallDetailsNotFoundException() {
		super();
	}
	public CallDetailsNotFoundException(String format) {
		super(format);
	}

}
