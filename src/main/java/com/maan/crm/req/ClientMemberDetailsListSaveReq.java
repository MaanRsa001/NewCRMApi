package com.maan.crm.req;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientMemberDetailsListSaveReq {

	@JsonProperty("ClientMemberDetailsList")
	private List<ClientMemberDetailsReq> clientMemberDetailsList ; 
	
	
}
