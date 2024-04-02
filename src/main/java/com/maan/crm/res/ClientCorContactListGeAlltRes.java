package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientCorContactListGeAlltRes {

	
	@JsonProperty("ClientRefNo")
    private String clientRefNo; 
	
	@JsonProperty("ClientCorContactList")
	private List<ClientCorContactGetAllRes> clientcorcontactList;
	
	}
