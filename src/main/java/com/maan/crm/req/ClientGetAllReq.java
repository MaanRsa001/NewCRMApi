package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientGetAllReq {

	@JsonProperty("InsuranceId")
	private String insId ;
	@JsonProperty("BranchCode")
	private String branchCode ;
	@JsonProperty("Limit")
	private String limit ;
	@JsonProperty("Offset")
	private String offset ;
	@JsonProperty("CreatedBy")
	private String createdBy;
}
