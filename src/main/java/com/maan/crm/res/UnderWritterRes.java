package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UnderWritterRes {

	@JsonProperty("ClassDesc")
	private String classDesc;
	
	@JsonProperty("ClassId")
	private String classId;
	
	@JsonProperty("PolicyType")
	private String policyType;
	
	@JsonProperty("PolicyTypeId")
	private String policyTypeId;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("SumInsuredEnd")
	private String sumInsuredEnd;
	
	@JsonProperty("SumInsuredStart")
	private String sumInsuredStart;
	
	
	
   
}
