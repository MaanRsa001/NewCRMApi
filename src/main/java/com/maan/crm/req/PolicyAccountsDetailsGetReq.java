package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyAccountsDetailsGetReq {

	@JsonProperty("PolicyId")
	private Integer policyId ;
	
	@JsonProperty("PolicyAccId")
	private Integer policyAccId;
	
	
}
