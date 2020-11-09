package com.cg.fms.exception;

public class ValueInvalidException extends Exception{

	public ValueInvalidException() {
		super();
	}

	public ValueInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValueInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValueInvalidException(String message) {
		super(message);
	}

	public ValueInvalidException(Throwable cause) {
		super(cause);
	}
	
	
	
}
