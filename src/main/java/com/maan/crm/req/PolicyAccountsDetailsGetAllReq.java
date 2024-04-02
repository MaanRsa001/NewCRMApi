package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyAccountsDetailsGetAllReq {


	@JsonProperty("Limit")
	private String limit ;
	@JsonProperty("Offset")
	private String offset ;
	
	@JsonProperty("PolicyAccId")
	private Integer policyAccId;
	@JsonProperty("Policyid")
	private Integer policyid;
	
}
