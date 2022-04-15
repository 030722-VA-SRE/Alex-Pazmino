package com.revature.exceptions;

public class PlatformNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PlatformNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlatformNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PlatformNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PlatformNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PlatformNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
