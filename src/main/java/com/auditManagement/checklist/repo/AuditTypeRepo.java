package com.auditManagement.checklist.repo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.auditManagement.checklist.domain.AuditType;

import lombok.RequiredArgsConstructor;

@Repository @RequiredArgsConstructor
public class AuditTypeRepo {
	
	private final EntityManager em;
	public AuditType findByType(String type) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AuditType> cq = cb.createQuery(AuditType.class);
		
		Root<AuditType> auditType = cq.from(AuditType.class);
		Predicate typePredicate = cb.equal(auditType.get("type"), type);
		cq.where(typePredicate);
		
		TypedQuery<AuditType> query = em.createQuery(cq);
		return query.getSingleResult();
	}
}
