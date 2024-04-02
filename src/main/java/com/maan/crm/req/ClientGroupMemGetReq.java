package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientGroupMemGetReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	@JsonProperty("GroupMemberId")
	private String groupMemberId;
	
}
