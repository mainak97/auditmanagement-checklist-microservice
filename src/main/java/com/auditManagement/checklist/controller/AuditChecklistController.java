package com.auditManagement.checklist.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auditManagement.checklist.domain.AuditChecklistQuestion;
import com.auditManagement.checklist.domain.AuditType;
import com.auditManagement.checklist.exception.AuditTypeHeaderNotFoundException;
import com.auditManagement.checklist.exception.AuthenticationException;
import com.auditManagement.checklist.exception.NoQuestionsForAuditTypeException;
import com.auditManagement.checklist.models.ErrorResponse;
import com.auditManagement.checklist.service.AuditTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class AuditChecklistController {
	private final AuditTypeService auditTypeService;
	private final RestTemplate restTemplate;
	
	@GetMapping("/auditchecklistquestions")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true,
		allowEmptyValue = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token"),
		@ApiImplicitParam(name = "audit-type", value = "Audit Type", required = true,
		allowEmptyValue = true, paramType = "header", dataTypeClass = String.class, example = "Type")
	})
	public void getAuditChecklistQuestions(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String authUri = "http://3.236.86.108:7000/api/authjwt";
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.add("Authorization", request.getHeader("Authorization"));
	    HttpEntity <String> entity = new HttpEntity<>(headers);
	    try {
		    restTemplate.exchange(authUri, HttpMethod.GET, entity, ErrorResponse.class);
	    } catch(Exception e) {
	    	throw new AuthenticationException(e.getMessage());
	    }
		String auditType = request.getHeader("audit-type");
		AuditType auditTypeObj = auditTypeService.findByType(auditType);
		if(auditType != null) {
			if(auditTypeObj == null) {
				throw new NoQuestionsForAuditTypeException("No Audit Type of type '"+auditType+"' was found");
			} else {
				List<AuditChecklistQuestion> questions = auditTypeObj.getQuestions();
				if(questions.isEmpty()) {
					throw new NoQuestionsForAuditTypeException("No questions found for Audit Type: "+auditType);
				}
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), questions);	
			}
		} else {
			throw new AuditTypeHeaderNotFoundException("audit-type header not found");
		}
	}
}
