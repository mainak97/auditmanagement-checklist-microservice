package com.auditManagement.checklist.exception;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1860741181457681948L;
	
	public AuthenticationException(String message) {
		super(message);
	}
}
