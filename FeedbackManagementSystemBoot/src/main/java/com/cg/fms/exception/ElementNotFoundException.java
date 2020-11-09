package com.cg.fms.exception;

public class ElementNotFoundException extends Exception{

	public ElementNotFoundException() {
		super();
	}

	public ElementNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ElementNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElementNotFoundException(String message) {
		super(message);
	}

	public ElementNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
}
