package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyDetailsGetAllReq {

	@JsonProperty("Limit")
	private String limit ;
	@JsonProperty("Offset")
	private String offset ;
	@JsonProperty("CreatedBy")
	private String createdBy;
	

	@JsonProperty("InsCompanyId")
	private String insCompanyId;

	@JsonProperty("BranchCode")
	private String branchCode;
}
