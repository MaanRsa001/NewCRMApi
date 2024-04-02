package com.maan.crm.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DashBoardCardsDataCountReq {

	@JsonProperty("InsuranceId")
	private String insId ;
	
	@JsonProperty("BranchCode")
	private String branchCode ;
	
	@JsonProperty("StartDate")
	private String startDate ;
	
	@JsonProperty("EndDate")
	private String endDate ;
}
