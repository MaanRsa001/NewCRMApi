package com.maan.crm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@IdClass(TicketMasterAdminId.class)
@Table(name="ticket_admin")
public class TicketMasterAdmin {

	
	public static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="TICKET_ID",length = 100, nullable = false)
	private String ticketId;
	
	@Column(name="CLIENT_REF_NO", length=100)
	private String clientRefNo;
	
	@Column(name="CLIENT_NAME",length=100)
	private String clientName;
	
	@Column(name="BRANCH_CODE",length=50)
	private String branchCode;
	
	@Column(name="REGION_CODE",length=50)
	private String regionCode;
	
	@Column(name="MOBILE_NUMBER",length=10)
	private String mobileNumber;
	
	@Column(name="EMAIL_ID",length=100)
	private String emailId;	

	@Column(name="STATUS",length=10)
	private String status;	


	

	@Column(name="ISSUER_ID")
	private Integer issuerId;
	
	@Column(name="ISSUER_NAME",length=100)
	private String issuerName;
	
	@Column(name="SNO")
	private Integer sno;


	@Column(name="STATUS_ID")
	private Integer statusId;	
	
	
	
	@Column(name="ISSUE_ID")
	private Integer issueId;
	
	@Column(name="ISSUE",length=100)
	private String issue;
	
	
	@Column(name="REMARKS",length=300)
	private String remarks;

	@Column(name="INS_COMPANY_ID",length=100)
	private String insCompanyId;

	
	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	

	@Column(name="LEAD_ID",length=100)
	private String leadId;
	

	@Column(name="POLICY_NUMBER",length=100)
	private String policyNumber;
	
	
	}
