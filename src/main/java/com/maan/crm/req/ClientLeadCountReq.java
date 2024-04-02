package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientLeadCountReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("InsuranceId")
    private String     insCompanyId ;
	
	@JsonProperty("BranchCode")
    private String     branchCode ;
	
	@JsonProperty("limit")
    private String     limit ;
	
	@JsonProperty("offset")
    private String     offset ;
	
	@JsonProperty("Type")
    private String     type ;
	
}
