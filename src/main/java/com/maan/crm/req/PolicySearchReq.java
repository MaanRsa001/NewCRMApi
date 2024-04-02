package com.maan.crm.req;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicySearchReq {

	@JsonProperty("SearchValue")
	private String searchValue ;
	
	@JsonProperty("CreatedBy")
	private String createdBy ;
	
	@JsonProperty("BranchCode")
	private String branchCode;
	@JsonProperty("InsCompanyId")
	private String insId ;
	

	@JsonProperty("Limit")
	private String limit;
	@JsonProperty("Offset")
	private String offset;
}
