package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientCrConGetReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	@JsonProperty("ContactId")
	private String clientcorContactId ;
	
}
