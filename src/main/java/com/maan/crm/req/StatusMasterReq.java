package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StatusMasterReq {

	@JsonProperty("StatusCode")
	private String statusCode;
	
	@JsonProperty("UserType")
	private String userType;
	
	@JsonProperty("SubStatusCode")
	private String subStatusCode;
}
