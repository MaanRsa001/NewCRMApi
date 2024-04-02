package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuoteSuccessRes {

	@JsonProperty("Response")
	private String response;
	
	@JsonProperty("QuoteNo")
	private String quoteNo;
	
	
}
