package com.auditManagement.checklist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.auditManagement.checklist.repo.AuditTypeRepo;
import com.auditManagement.checklist.service.AuditTypeServiceImpl;

@WebMvcTest(AuditTypeServiceImpl.class)
class AuditManagementChecklistServiceTests {
	@Mock
	private AuditTypeRepo auditTypeRepo;
	
	@MockBean
	private AuditTypeServiceImpl auditTypeServiceImpl;
	
	@InjectMocks
	private AuditTypeServiceImpl auditTypeService;
	
	
	@Test
    void userNotFoundTest() throws Exception {
    	Mockito.when(auditTypeRepo.findByType("")).thenReturn(null);
    	assertThat(auditTypeService.findByType("")).isNull();
    	
    }
}
