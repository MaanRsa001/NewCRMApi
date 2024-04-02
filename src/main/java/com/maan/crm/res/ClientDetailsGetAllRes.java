package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ClientDetailsGetAllRes {
	

	
	@JsonProperty("ClientCount")
	private Long clientCount;
	
	@JsonProperty("ClientDetails")
	private  List<ClientDetailsGridRes> clientDetails;
}
