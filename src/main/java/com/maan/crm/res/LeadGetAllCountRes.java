package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class LeadGetAllCountRes {
	

	
	@JsonProperty("LeadCount")
	private Long leadCount;
	
	@JsonProperty("LeadDetails")
	private  List<CrmLeadRes> leadDetails;
}
