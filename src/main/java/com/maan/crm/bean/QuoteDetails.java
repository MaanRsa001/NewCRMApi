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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(QuoteDetailsId.class)
@Table(name = "quote_details")
public class QuoteDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	// --- ENTITY PRIMARY KEY
	@Id
	@Column(name = "REFERENCE_NO", nullable = false, length = 100)
	private String referenceNo;

	// --- ENTITY DATA FIELDS
	@Column(name = "QUOTE_NO")
	private String quoteNo;

	@Column(name = "POLICY_NO")
	private String policyNo;

	@Column(name = "TOTAL_PREMIUM")
	private Double totalPremium;

	@Column(name = "CUSTOMER_ID")
	private String customerId;

	@Column(name = "PRODUCT_ID")
	private Integer productId;

	@Column(name = "STATUS", length = 10)
	private String status;

	@Column(name = "ENQUIRY_ID", nullable = false, length = 20)
	private String enquiryId;

	@Column(name = "LEAD_ID", nullable = false, length = 100)
	private String leadId;

	@Column(name = "CLIENT_REF_NO", nullable = false, length = 100)
	private String clientRefNo;

	@Column(name = "CLASS_ID")
	private Integer classId;

	@Column(name = "CLASS_DESC")
	private String classDesc;

	@Column(name = "POLICYTYPE_ID")
	private Integer policytypeId;

	@Column(name = "POLICYTYPE")
	private String policytype;

	@Column(name = "SUM_INSURED")
	private Double sumInsured;

	@Column(name = "SUGGEST_PREMIUM")
	private Double suggestPremium;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_USERTYPE")
	private String createdUsertype;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATER_USERTYPE")
	private String updaterUsertype;

	@Column(name = "LAST_REMARKS")
	private String lastRemarks;

	@Column(name = "QUOTE_STATUS")
	private String quoteStatus;

	@Column(name = "QUOTE_STATUS_DESC")
	private String quoteStatusDesc;

	@Column(name = "QUOTE_DESC")
	private String quoteDesc;

	@Column(name = "INS_COMPANY_ID")
	private String insCompanyId;

	@Column(name = "BRANCH_CODE")
	private String branchCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "ACCEPTED_DATE")
	private Date acceptedDate;

	@Column(name = "ACCEPTED_PREMIUM")
	private Double acceptedPremium;

	@Temporal(TemporalType.DATE)
	@Column(name = "REJECTED_DATE")
	private Date rejectedDate;

	@Column(name = "REJECTED_REASON")
	private String rejectedReason;

	@Column(name = "TOTAL_PREMIUM_INCLUDE_TAX")
	private Double totalPremiumIncludeTax;

	@Column(name = "APPROVED_BY")
	private String approvedBy;

	@Column(name = "APPROVED_STATUS")
	private String approvedStatus;

	@Column(name = "APPROVED_REMARKS")
	private String approvedRemarks;

	@Column(name = "APPR_CONFORM_STATUS")
	private String apprConformStatus;

	@Column(name = "GROSS_PREMIUM")
	private Double grossPremium;

	@Column(name = "TAX_PERCENT")
	private Integer taxPercent;

	@Column(name = "TAX_AMOUNT")
	private Double taxAmount;

	@Temporal(TemporalType.DATE)
	@Column(name = "ENTRY_DATE")
	private Date entryDate;

	@Column(name = "ENQ_CREATED_BY", length = 100)
	private String enqCreatedBy;

	@Column(name = "ENQ_CREATER_USERTYPE", length = 100)
	private String enqCreaterUsertype;
	
	@Column(name = "PREMIUM_RATE")
	private Double premiumRate;
	
	@Column(name = "ADD_INFO_DISCOUNT")
	private Integer addInfoDiscount;

}
