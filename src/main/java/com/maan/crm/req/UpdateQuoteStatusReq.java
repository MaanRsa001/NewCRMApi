package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateQuoteStatusReq {

	@JsonProperty("SubStatusCode")
	private String subStatusCode;

	@JsonProperty("UserType")
	private String userType;

	@JsonProperty("InsuranceId")
	private String insuranceId;

	@JsonProperty("BranchCode")
	private String branchCode;

	@JsonProperty("LeadId")
	private String leadId;

	@JsonProperty("EnquiryId")
	private String enquiryId;

	@JsonProperty("ClientRefNo")
	private String clientRefNo;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("TotalPrmium")
	private String totalPremium;

/*	@JsonProperty("GrossPrmium")
	private String grossPremium;
	
	 @JsonProperty("SuggestedPrmium")
	private String suggestedPremium;
	 */

	@JsonProperty("TechnicalDiscount")
	private String tax;

	

	@JsonProperty("SumInsured")
	private String sumInsured;

	@JsonProperty("QuoteDesc")
	private String quoteDesc;
	
	@JsonProperty("AddInfoDiscount")
	private String addInfoDiscount;
	
	@JsonProperty("PremiumRate")
	private String premiumRate;
	
	@JsonProperty("QuoteStatusDesc")
	private String quoteStatusDesc;

	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("ReferenceNo")
	private String referenceNo;

	@JsonProperty("QuoteNo")
	private String quoteNo;
}
