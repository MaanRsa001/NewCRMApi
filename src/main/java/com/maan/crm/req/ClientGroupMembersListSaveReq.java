package com.maan.crm.req;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientGroupMembersListSaveReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("ClientGroupMembersList")
	private List<ClientGroupMembersReq> clientGroupMembersList ; 
	
	
}
