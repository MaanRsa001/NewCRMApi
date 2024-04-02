package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DashBoardCardsDataCountRes {

	@JsonProperty("ActiveClientCount")
	private Long activeClientCount ;
	
	@JsonProperty("OpenLeadCount")
	private Long openLeadCount ;
	
	@JsonProperty("OpenProspectCount")
	private Long openProspectCount ;
	
	@JsonProperty("ActivePolicyCount")
	private Long activePolicyCount ;
	
	
	
	
}
