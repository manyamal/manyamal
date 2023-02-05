package com.lucy.devproblem.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 529279913591839821L;

	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(Exception ex) {
		super(ex);
	}
}
