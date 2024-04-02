package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientLeadsGridRes {

	@JsonProperty("LeadList")
	private List<CrmLeadRes> leadList ;
	@JsonProperty("EnquiryList")
	private List<EnquiryGridRes> enquiryList ;
	@JsonProperty("QuoteList")
	private List<QuoteGridRes> quoteList ;
	@JsonProperty("PolicyList")
	private List<PolicyDetailsGetAllRes> policyList ;
	
	
}
