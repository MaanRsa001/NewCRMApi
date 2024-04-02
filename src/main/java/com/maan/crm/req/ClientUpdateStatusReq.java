package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientUpdateStatusReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	
	@JsonProperty("LeadId")
	private String leadId ;
	@JsonProperty("BranchCode")
	private String branchCode ;
	@JsonProperty("InsuranceId")
	private String insuranceId ;
	@JsonProperty("CreatedBy")
	private String createdBy ;
	@JsonProperty("UserType")
	private String userType ;
	@JsonProperty("Status")
	private String status ;
	@JsonProperty("QuoteNo")
	private String quoteNo ;
	@JsonProperty("ReferenceNo")
	private String referenceNo ;
	@JsonProperty("PolicyNo")
	private String policyNo ;
	
}
