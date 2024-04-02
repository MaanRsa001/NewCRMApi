package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyAssuredDetailsGetAllReq {


	@JsonProperty("Limit")
	private String limit ;
	@JsonProperty("Offset")
	private String offset ;
	
	@JsonProperty("RiderId")
	private Integer riderId;
	@JsonProperty("PolicyId")
	private Integer policyId;
	
}
