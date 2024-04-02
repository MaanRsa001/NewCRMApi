package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SearchEnquiryReq {

	@JsonProperty("SearchValue")
	private String searchValue;
	

	@JsonProperty("Limit")
	private String limit;
	

	@JsonProperty("Offset")
	private String offset;


}
