package com.maan.crm.req;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientDetailsJsonTemReq {

	@JsonProperty("ClientDetails")
	private ClientDetailsSaveReq clientDetails;

	@JsonProperty("OtherDetails")
	private ClientDetailsUpdateReq otherDetails;

	@JsonProperty("ClientAddressDetails")
	private List<ClientAddressDetailsSaveReq> clientAddressDetails;
	
	@JsonProperty("ClientCorDetails")
	private List<ClientCorContactReq> clientCorDetails;
	
	@JsonProperty("ClientMemberDetails")
	private List<ClientMemberDetailsReq> clientMemberDetails;
	
	

	
	
}
