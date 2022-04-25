package com.auditManagement.checklist.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Table(name="Audit_Checklist_Question")
public class AuditChecklistQuestion {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(name="question", nullable=false)
	private String question;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private AuditType auditType;
}
