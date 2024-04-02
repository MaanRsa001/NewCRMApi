package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientMemberDetailsGetReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	@JsonProperty("ClientMemberId")
	private String clientMemberId;
	
}
