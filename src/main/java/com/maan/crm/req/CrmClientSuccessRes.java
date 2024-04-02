package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CrmClientSuccessRes {

	@JsonProperty("Response")
	private String response ;
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
}
