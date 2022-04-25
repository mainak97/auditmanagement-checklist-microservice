package com.auditManagement.checklist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.auditManagement.checklist.domain.AuditType;
import com.auditManagement.checklist.repo.AuditTypeRepo;

@DataJpaTest
class AuditTypeRepoTests {
	
	@Mock
	private EntityManager em;
	
	@InjectMocks
	private AuditTypeRepo auditTypeRepo;
	
	@Mock
    private CriteriaBuilder criteriaBuilder;
	
	@Mock
    private CriteriaQuery<AuditType> criteriaQuery;
	
	@Mock
	private Root<AuditType> auditType;
	
	@Mock
	private Predicate predicate;
	
	@Mock
	private TypedQuery<AuditType> typedQuery;
	
	@Test
	void findByTypeTest() {
		when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		when(criteriaBuilder.createQuery(AuditType.class)).thenReturn(criteriaQuery);
		when(criteriaQuery.from(AuditType.class)).thenReturn(auditType);
		when(criteriaBuilder.equal(auditType.get("type"),"")).thenReturn(predicate);
		when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
		assertThat(auditTypeRepo.findByType("")).isNull();
	}
}
