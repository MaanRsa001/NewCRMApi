package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UnderWritterDetailsReq {

	@JsonProperty("ClassDesc")
	private String classDesc ;
	
	@JsonProperty("ClassId")
	private String classId ;
	
	@JsonProperty("PolicyTypeId")
	private String policyTypeId ;
	
	@JsonProperty("PolicyType")
	private String policyType ;
	
	@JsonProperty("SumInsuredStart")
	private String sumInsuredStart ;
	
	@JsonProperty("SumInsuredEnd")
	private String sumInsuredEnd ;
	
	@JsonProperty("Status")
	private String status ;
}
