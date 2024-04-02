package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocGroupReq {

	@JsonProperty("InsuranceId")
	private String insuranceId ;
	
	@JsonProperty("BranchCode")
	private String branchCode ;
}
