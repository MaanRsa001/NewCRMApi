package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DashBoardQuoteStatusCountRes {

	
	@JsonProperty(" BusinessLostCount ")
	private String BusinessLostCount ;
	
	@JsonProperty("BusinessConverted")
	private String BusinessConverted ;

	@JsonProperty("FreshQuote") 
	private String FreshQuote ;
	
	
	@JsonProperty("TotalFollowupsCount")
	private String TotalFollowupsCount ;
	
	
}
