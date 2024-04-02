package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadGenerateReq {

	@JsonProperty("LeadId")
	private String leadId;
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo;


	@JsonProperty("InsuranceId")
    private String     insCompanyId ;
	
	@JsonProperty("BranchCode")
    private String     branchCode ;
	
	@JsonProperty("RegionCode")
    private String     regionCode ;
    
	@JsonProperty("CreatedBy")
    private String     createdBy ;
	
	@JsonProperty("UserType")
    private String     userType ;
	
}
