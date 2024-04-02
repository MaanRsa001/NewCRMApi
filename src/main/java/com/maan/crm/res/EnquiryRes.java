package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EnquiryRes {

	@JsonProperty("SubStatusCode")
	private String subStatusCode;
	

	@JsonProperty("SubStatusName")
	private String subStatusName;
	
	@JsonProperty("Count")
	private Long count;
}
