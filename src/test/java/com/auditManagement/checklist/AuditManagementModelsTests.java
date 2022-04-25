package com.auditManagement.checklist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.auditManagement.checklist.domain.AuditChecklistQuestion;
import com.auditManagement.checklist.domain.AuditType;
import com.auditManagement.checklist.models.ErrorResponse;

@SpringBootTest
class AuditManagementModelsTests {
	@Test
	void ErrorResponseTest() {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(1000);
		response.setErrorMsg("error");
		assertThat(response.getErrorCode()).isEqualTo(1000);
		assertThat(response.getErrorMsg()).isEqualTo("error");
		assertThat(response.toString()).hasToString("ErrorResponse(errorCode=1000, errorMsg=error)");
		ErrorResponse response1 = new ErrorResponse(1000,"error");
		assertThat(response1).isNotNull();
	}
	@Test
	void AuditTypeTest() {
		AuditType auditType = new AuditType(1L,"",null);
		auditType.setId(1L);
		auditType.setType("");
		auditType.setQuestions(null);
		assertThat(auditType).isNotNull();
		assertThat(auditType.getId()).isEqualTo(1L);
		assertThat(auditType.getType()).isEmpty();
		assertThat(auditType.getQuestions()).isNull();
	}
	@Test
	void AuditChecklistQuestionTest() {
		AuditChecklistQuestion question = new AuditChecklistQuestion(1L,"",null);
		question.setId(1L);
		question.setQuestion("");
		question.setAuditType(null);
		assertThat(question).isNotNull();
		assertThat(question.getId()).isEqualTo(1L);
		assertThat(question.getQuestion()).isEmpty();
		assertThat(question.getAuditType()).isNull();
	}
}
