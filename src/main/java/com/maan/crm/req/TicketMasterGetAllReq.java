package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TicketMasterGetAllReq {

	@JsonProperty("BranchCode")
	private String branchCode;
	

	@JsonProperty("RegionCode")
	private String regionCode;
	

	@JsonProperty("InsuranceId")
	private String insCompanyId;
}
