package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SuccessRes {
	
	@JsonProperty("Response")
	private String response;

	@JsonProperty("SucessId")
	private String sucessId;
	
}
