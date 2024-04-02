package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetbyEnquiryQuoteReq {

	@JsonProperty("EnquiryId")
	private String enquiryId;
	

	@JsonProperty("Limit")
	private String limit;
	

	@JsonProperty("Offset")
	private String offset;
}
