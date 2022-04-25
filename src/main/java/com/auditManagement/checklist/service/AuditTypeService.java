package com.auditManagement.checklist.service;

import com.auditManagement.checklist.domain.AuditType;

public interface AuditTypeService {
	AuditType findByType(String type);
}
