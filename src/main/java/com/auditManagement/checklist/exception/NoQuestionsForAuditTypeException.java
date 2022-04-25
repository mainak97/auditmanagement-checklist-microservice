package com.auditManagement.checklist.exception;

public class NoQuestionsForAuditTypeException extends RuntimeException {

	private static final long serialVersionUID = 5350033454533291289L;
		
	public NoQuestionsForAuditTypeException(String message) {
		super(message);
	}
	
}
