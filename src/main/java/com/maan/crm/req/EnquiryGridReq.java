package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EnquiryGridReq {

	@JsonProperty("InsuranceId")
	private String insCompanyId;
	
	@JsonProperty("BranchCode")
	private String branchCode;
	
	@JsonProperty("UserType")
	private String userType;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("SubStatusCode")
	private String subStatusCode;
	
	@JsonProperty("Limit")
	private String limit;
	
	@JsonProperty("Offset")
	private String offset ;
	
}
