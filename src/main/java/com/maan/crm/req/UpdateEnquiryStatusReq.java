package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateEnquiryStatusReq {

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

	@JsonProperty("AllotedUW")
	private String allotedUW;
	
	@JsonProperty("AlloterUWId")
	private String alloterUWId;
	
	@JsonProperty("PremiumRate")
	private String premiumRate;
	
	@JsonProperty("SuggestedPrmium")
	private String suggestedPremium;
	
	@JsonProperty("SumInsured")
	private String sumInsured;
	
	@JsonProperty("EnquiryDesc")
	private String enquiryDesc;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("BroughtBy")
	private String broughtBy;
	
	@JsonProperty("BroughtCode")
	private String broughtCode;
	
	@JsonProperty("BroughtName")
	private String broughtName;
	
	
	
}
