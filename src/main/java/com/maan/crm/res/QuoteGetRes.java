package com.maan.crm.res;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuoteGetRes {

	@JsonProperty( "ReferenceNo")
	private String referenceNo;
	
	@JsonProperty( "QuoteNo")
	private String quoteNo;

	@JsonProperty( "PolicyNo")
	private String policyNo;

	@JsonProperty( "TotalPremium")
	private String totalPremium;

	@JsonProperty( "CustomerId")
	private String customerId;

	@JsonProperty( "ProductId")
	private String productId;

	@JsonProperty( "Status")
	private String status;

	@JsonProperty( "EnquiryId")
	private String enquiryId;

	@JsonProperty( "LeadId")
	private String leadId;

	@JsonProperty( "ClientRefNo")
	private String clientRefNo;

	@JsonProperty( "EnquiryDescription")
	private String enquiryDescription;

	@JsonProperty( "BusinessType")
	private String businessType;

	@JsonProperty( "BusinessTypeId")
	private String businessTypeId;

	@JsonProperty( "ClassId")
	private String classId;

	@JsonProperty( "ClassDesc")
	private String classDesc;

	@JsonProperty( "PolicytypeId")
	private String policytypeId;

	@JsonProperty( "Policytype")
	private String policytype;

	@JsonProperty( "SumInsured")
	private String sumInsured;

	@JsonProperty( "SuggestPremium")
	private String suggestPremium;

	@JsonProperty( "CreatedBy")
	private String createdBy;

	@JsonProperty( "CreatedUsertype")
	private String createdUsertype;

	@JsonFormat(pattern="dd/MM/yyyy")
	@JsonProperty( "UpdatedDate")
	private Date updatedDate;

	@JsonProperty( "UpdatedBy")
	private String updatedBy;

	@JsonProperty( "UpdaterUsertype")
	private String updaterUsertype;

	@JsonProperty( "LastRemarks")
	private String lastRemarks;

	@JsonProperty( "QuoteStatus")
	private String quoteStatus;

	@JsonProperty( "QuoteStatusDesc")
	private String quoteStatusDesc;

	@JsonProperty( "QuoteDesc")
	private String quoteDesc;

	@JsonFormat(pattern="dd/MM/yyyy")
	@JsonProperty( "EntryDate")
	private Date entryDate;

	@JsonProperty( "InsCompanyId")
	private String insCompanyId;

	@JsonProperty( "branchCode")
	private String branchCode;

	@JsonFormat(pattern="dd/MM/yyyy")
	@JsonProperty( "AcceptedDate")
	private Date acceptedDate;

	@JsonProperty( "AcceptedPremium")
	private String acceptedPremium;

	@JsonFormat(pattern="dd/MM/yyyy")
	@JsonProperty( "RejectedDate")
	private Date rejectedDate;

	@JsonProperty( "RejectedReason")
	private String rejectedReason;

	@JsonProperty( "TotalPremiumIncludeTax")
	private String totalPremiumIncludeTax;

	@JsonProperty( "ApprovedBy")
	private String approvedBy;

	@JsonProperty( "ApprovedStatus")
	private String approvedStatus;

	@JsonProperty( "ApprovedRemarks")
	private String approvedRemarks;

	@JsonProperty( "ApprConformStatus")
	private String apprConformStatus;
	
	@JsonProperty( "PolicyCount")
	private String policyCount;
	
	@JsonProperty("TechnicalDiscount")
	private String tax;

	@JsonProperty("AddInfoDiscount")
	private String addInfoDiscount;
	
	@JsonProperty("PremiumRate")
	private String premiumRate;
}
