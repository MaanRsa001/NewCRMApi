package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyRiderDetailsGetReq {

	@JsonProperty("PolicyId")
	private Integer policyId ;
	
	@JsonProperty("RiderId")
	private Integer riderId ;
}
