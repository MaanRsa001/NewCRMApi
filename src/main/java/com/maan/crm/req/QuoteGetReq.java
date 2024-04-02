package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuoteGetReq {

	@JsonProperty("InsuranceId")
	private String insCompanyId;
	
	@JsonProperty("BranchCode")
	private String branchCode;
	
	@JsonProperty("UserType")
	private String userType;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("QuoteNo")
	private String quoteNo;
}
