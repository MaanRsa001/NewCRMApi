package com.maan.crm.req;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientCrConListSaveReq {

	@JsonProperty("ClientDetailsList")
	private List<ClientCorContactReq> clientDetailsList ; 
	
	
}
