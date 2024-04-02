package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyAssuredDetailsGetReq {

	@JsonProperty("PolicyId")
	private Integer policyId ;
	
	@JsonProperty("AssuredId")
	private Integer assuredId; 

}
