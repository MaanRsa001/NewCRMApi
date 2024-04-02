package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientMemberDetailsGetAllReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	
}
