package com.maan.crm.req;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadSearchReq {

	@JsonProperty("SearchValue")
	private String searchValue ;
	@JsonProperty("CreatedBy")
	private String createdBy ;
	@JsonProperty("InsuranceId")
	private String insCompanyId;
	@JsonProperty("BranchCode")
	private String branchCode ;
	@JsonProperty("Limit")
	private String limit;
	@JsonProperty("Offset")
	private String offset ;
	@JsonProperty("RegionCode")
	private String regionCode ;

}
