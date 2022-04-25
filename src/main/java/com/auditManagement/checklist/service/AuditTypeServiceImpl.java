package com.auditManagement.checklist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auditManagement.checklist.domain.AuditType;
import com.auditManagement.checklist.repo.AuditTypeRepo;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Transactional
public class AuditTypeServiceImpl implements AuditTypeService{

	private final AuditTypeRepo auditTypeRepo;
	@Override
	public AuditType findByType(String type) {
		return auditTypeRepo.findByType(type);
	}

}
