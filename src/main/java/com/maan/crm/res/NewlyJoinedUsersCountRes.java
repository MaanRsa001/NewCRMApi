package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NewlyJoinedUsersCountRes {

	@JsonProperty("CallCentreEmpCount")
	private Long callCentreEmpCount ;
	
	@JsonProperty("SalesManagerCount")
	private Long salesManagerCount ;
	
	@JsonProperty("UnderWritterCount")
	private Long underWritterCount ;
	
	@JsonProperty("AdminCount")
	private Long adminCount ;
	
	@JsonProperty("ClientsCount")
	private Long clientsCount ;
}
