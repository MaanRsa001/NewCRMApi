package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientAddressDetailsGetReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	@JsonProperty("ClientAddressId")
    private String clientAddressId ; 
}
