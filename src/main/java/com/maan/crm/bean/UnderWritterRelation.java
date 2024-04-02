package com.maan.crm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="underwritter_relation")
@IdClass(UnderWritterRelationId.class)
public class UnderWritterRelation {

	@Id
	@Column(name="UW_CODE",length=20)
	private String uwCode;

	@Id
	@Column(name="CLASS_ID",length=10)
	private Long classId;

	@Id	
	@Column(name="POLICY_TYPE_ID",length=10)
	private Long policyTypeId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="LOGIN_ID",length=100)
	private String loginId;
	
	@Column(name="CLASS_DESC",length=100)
	private String classDesc;

	

	@Column(name="POLICY_TYPE",length=100)
	private String policyType;
	

	@Column(name="CUSTOMER_SIZE",length=20)
	private String customerSize;
	
	@Column(name="STATUS",length=1)
	private String status;
	

	@Column(name="REMARKS",length=500)
	private String remarks;
	
	@Column(name="SUMINSURED_START_LIMIT")
	private Double suminsuredStartLimit;
	

	@Column(name="SUMINSURED_END_LIMIT")
	private Double suminsuredEndLimit;
	
	@Column(name="LICENSE_ID")
	private Integer licenseId;
}
