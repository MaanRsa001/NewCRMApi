package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientCrContactGetAllReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	@JsonProperty("Limit")
	private String limit ;
	@JsonProperty("Offset")
	private String offset ;
	
}
