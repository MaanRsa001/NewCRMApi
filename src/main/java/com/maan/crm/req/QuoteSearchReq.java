package com.maan.crm.req;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuoteSearchReq {


	@JsonProperty("SearchValue")
	private String searchValue ;
	
	@JsonProperty("CreatedBy")
	private String createdBy ;
	
	@JsonProperty("InsuranceId")
	private String insId ;
	
	@JsonProperty("BranchCode")
	private String branchCode ;
	
	@JsonProperty("Limit")
	private String limit;
	
	@JsonProperty("Offset")
	private String offset ;
	
}
