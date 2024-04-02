package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UnderwriterReq {

	@JsonProperty("BranchCode")
	private String branchCode;
	

	@JsonProperty("InsuranceId")
	private String insCompanyId;
	
	@JsonProperty("ClassId")
	private String classId;
	
	@JsonProperty("PolicyTypeId")
	private String policyTypeId;
	
	@JsonProperty("SumInsured")
	private String sumInsured;
}
