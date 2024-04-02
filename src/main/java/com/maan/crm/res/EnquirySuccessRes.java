package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EnquirySuccessRes {

	@JsonProperty("Response")
	private String response;
	
	@JsonProperty("EnquiryId")
	private String enquiryId;
	
	
}
