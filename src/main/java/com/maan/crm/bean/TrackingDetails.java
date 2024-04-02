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
@Table(name="tracking_details")
@IdClass(TrackingDetailsId.class)

public class TrackingDetails {

	public static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="TRACKING_ID", length=100, nullable = false)
	private String trackingId;
	
	@Column(name="INS_COMPANY_ID", length=100)
	private String insCompanyId;
	
	@Column(name="BRANCH_CODE", length=100)
	private String branchCode;
	
	@Column(name="REGION_CODE", length=100)
	private String regionCode;
	
	@Column(name="STATUS", length=100)
	private String status;
	
	@Column(name="STATUS_DESCRIPTION", length=100)
	private String statusDescription;
	

	@Column(name="CREATED_BY", length=100)
	private String createdBy;
	

	@Column(name="CLIENT_REF_NO", length=100)
	private String clientRefNo;
	

	@Column(name="CLIENT_NAME", length=100)
	private String clientName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;

	@Column(name="LEAD_ID", length=100)
	private String leadId;

	@Column(name="PROSPECT_ID", length=100)
	private String prospectId;

	@Column(name="POLICY_ID", length=100)
	private String policyId;



}
