package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.bean.LeadDetails;

import lombok.Data;

@Data
public class ClientsLeadCountRes {

	@JsonProperty("LeadCount")
	private List<CrmLeadRes> lead ;
	
	@JsonProperty("ProspectCount")
	private List<CrmLeadRes> prospect;
	
	@JsonProperty("PolicyCount")
	private List<PolicyDetailsRes> policy;
}
