package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginDetailsGetReq {

	@JsonProperty("InsuranceId")
	private String companyId ;
	@JsonProperty("BranchCode")
	private String branchCode ;
	@JsonProperty("Limit")
	private String limit ;
	@JsonProperty("Offset")
	private String offset ;

}
