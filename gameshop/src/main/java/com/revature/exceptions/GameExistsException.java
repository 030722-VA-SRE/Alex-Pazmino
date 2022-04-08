package com.revature.exceptions;

public class GameExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public GameExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GameExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GameExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GameExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GameExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
