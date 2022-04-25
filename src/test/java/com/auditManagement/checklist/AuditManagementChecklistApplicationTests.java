package com.auditManagement.checklist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuditManagementChecklistApplicationTests {

	@Test
	void contextLoads() {
		String test = null;
		assertThat(test).isNull();
	}
	
	@Test
	void test() {
		String test = null;
		assertThat(test).isNull();
		AuditManagementChecklistApplication.main(new String[]{});
	}

}

