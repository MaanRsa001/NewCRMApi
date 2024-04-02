package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DashBoardTotalCountRes {

	@JsonProperty("TotalClientCount")
	private Long totalClientCount ;
	
	@JsonProperty("TotalLeadCount")
	private Long totalLeadCount ;
	
	@JsonProperty("TotalEnquiryCount")
	private Long totalEnquiryCount ;
	
	@JsonProperty("TotalQuoteCount")
	private Long totalQuoteCount ;
	
	@JsonProperty("TotalPolicyCount")
	private Long totalPolicyCount ;
	
}
