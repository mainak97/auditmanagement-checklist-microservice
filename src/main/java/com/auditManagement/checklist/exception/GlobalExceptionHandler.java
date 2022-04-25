package com.auditManagement.checklist.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auditManagement.checklist.models.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;



@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	 @ExceptionHandler(AuditTypeHeaderNotFoundException.class)
	 public ResponseEntity<ErrorResponse> handleAuditTypeHeaderNotFoundException(AuditTypeHeaderNotFoundException exception, 
		      WebRequest request){
		 log.error(exception.getMessage());
		 ErrorResponse response = new ErrorResponse(8000,exception.getMessage());
		 return ResponseEntity.status(400).body(response);
	 }
	 @ExceptionHandler(NoQuestionsForAuditTypeException.class)
	 public ResponseEntity<ErrorResponse> handleNoQuestionsForAuditTypeException(NoQuestionsForAuditTypeException exception, 
		      WebRequest request){
		 log.error(exception.getMessage());
		 ErrorResponse response = new ErrorResponse(8001,exception.getMessage());
		 return ResponseEntity.status(400).body(response);
	}
	 @ExceptionHandler(AuthenticationException.class)
	 public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException exception, 
		      WebRequest request){
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			log.info(exception.getMessage());
			ErrorResponse response = mapper.readValue(exception.getMessage().substring(7,exception.getMessage().length()-1),ErrorResponse.class);
			return ResponseEntity.status(Integer.parseInt(exception.getMessage().substring(0,3))).body(response);
			
		} catch (JsonProcessingException e) {
			log.error(exception.getMessage());
			ErrorResponse response = new ErrorResponse(8006,"Auth server error");
			return ResponseEntity.status(500).body(response);
		}
	}

}

