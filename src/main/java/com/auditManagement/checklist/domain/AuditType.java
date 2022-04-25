package com.auditManagement.checklist.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Table(name="Audit_Type")
public class AuditType {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(name="type", nullable=false)
	private String type;
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="auditType")
    private List<AuditChecklistQuestion> questions;
}
