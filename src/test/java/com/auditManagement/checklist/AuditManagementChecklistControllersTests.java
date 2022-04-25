package com.auditManagement.checklist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.auditManagement.checklist.controller.AuditChecklistController;
import com.auditManagement.checklist.domain.AuditChecklistQuestion;
import com.auditManagement.checklist.domain.AuditType;
import com.auditManagement.checklist.models.ErrorResponse;
import com.auditManagement.checklist.service.AuditTypeService;

@WebMvcTest(AuditChecklistController.class)
class AuditManagementAuthorizationControllersTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AuditTypeService auditTypeService;
	
	@MockBean
	private RestTemplate restTemplate;
	
    @Test
    void getQuestionsNoAuditTypeHeaderTest() throws Exception {
    	ErrorResponse error = new ErrorResponse();
	    ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(error,HttpStatus.FORBIDDEN);
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenReturn(response);
        mvc.perform(get("/api/auditchecklistquestions").header("Authorization", "Bearer simpletoken"))
          .andExpect(status().is4xxClientError());
    }
    @Test
    void getQuestionsTest() throws Exception {
    	ErrorResponse error = new ErrorResponse();
	    ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(error,HttpStatus.OK);
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenReturn(response);
    	ArrayList<AuditChecklistQuestion> question = new ArrayList<>();
    	question.add(new AuditChecklistQuestion());
    	Mockito.when(auditTypeService.findByType("header")).thenReturn(new AuditType(1L,"",question));
        mvc.perform(get("/api/auditchecklistquestions").header("audit-type", "header"))
          .andExpect(status().isOk());
    }
    @Test
    void noQuestionsTest() throws Exception {
    	ErrorResponse error = new ErrorResponse();
	    ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(error,HttpStatus.OK);
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenReturn(response);
    	ArrayList<AuditChecklistQuestion> question = new ArrayList<>();
    	Mockito.when(auditTypeService.findByType("header")).thenReturn(new AuditType(1L,"",question));
        mvc.perform(get("/api/auditchecklistquestions").header("audit-type", "header"))
          .andExpect(status().is4xxClientError());
    }
    @Test
    void auditTypeNotFoundTest() throws Exception {
    	ErrorResponse error = new ErrorResponse();
	    ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(error,HttpStatus.OK);
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenReturn(response);
    	Mockito.when(auditTypeService.findByType("header")).thenReturn(null);
        mvc.perform(get("/api/auditchecklistquestions").header("audit-type", "header"))
          .andExpect(status().is4xxClientError());
    }
    @Test
    void authErrorTest() throws Exception {
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenThrow(new RuntimeException("500 : '{}'"));
        mvc.perform(get("/api/auditchecklistquestions").header("Authorization", "Bearer simpletoken"))
          .andExpect(status().is5xxServerError());
    }
    @Test
    void authServerErrorTest() throws Exception {
    	Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ErrorResponse>>any())).thenThrow(new RuntimeException("500 : {}"));
        mvc.perform(get("/api/auditchecklistquestions").header("Authorization", "Bearer simpletoken"))
          .andExpect(status().is5xxServerError());
    }
}
