package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CampaignMasterId.class)
@Table(name="campaign_master")
public class CampaignMaster implements Serializable {

	@Id
	@Column(name="CAMPAIGN_ID",nullable=false,length=10)
	private String campaignId;
	

	@Column(name="PROMO_CODE", length=100, nullable=false)
	private String promoCode;
	
	@Column(name="CAMPAIGN_NAME", length=100)
	private String campaignName;
	
	@Column(name="CREATED_BY", length=100)
	private String createdBy;
	
	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="PRODUCT", length=100)
	private String product;
	
	@Column(name="DESCRIPTION", length=300)
	private String description;
	
	@Column(name="BRANCH_CODE", length=20)
	private String branchCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="STATUS",length=10)
	private String status;
	
	@Column(name="INS_COMPANY_ID", length=10)
	private String insCompanyId;
	
	@Column(name="CLASS_TYPE", length=100)
	private String classType;
	
	@Column(name="CLASS_TYPE_ID")
	private Integer classTypeId;
	
	@Column(name="POLICY_TYPE", length=100)
	private String policyType;
	
	@Column(name="POLICY_TYPE_ID")
	private Integer policyTypeId;

	@Column(name="LANDINGPAGE_LINK", length=1000)
	private String landingpageLink;


	@Column(name="USER_TYPE", length=100)
	private String userType;
	
	

	@Column(name="BUSINESS_TYPE", length=100)
	private String businessType;
	
	@Column(name="BUSINESS_TYPE_ID")
	private Integer businessTypeId;
}
