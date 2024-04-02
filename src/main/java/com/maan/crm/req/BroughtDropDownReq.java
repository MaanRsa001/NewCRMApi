package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BroughtDropDownReq {

	@JsonProperty("InsuranceId")
	private String insuranceId ;
	
	@JsonProperty("BranchCode")
	private String branchCode ;
	
	@JsonProperty("BroughtBy")
	private String broughtBy ;
	
	@JsonProperty("Limit")
	private String limit ;
	
	@JsonProperty("Offset")
	private String offset ;
}
