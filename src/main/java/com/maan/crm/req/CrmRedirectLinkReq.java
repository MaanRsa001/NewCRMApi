package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CrmRedirectLinkReq {

	@JsonProperty("LoginId")
	private String loginId ;
	@JsonProperty("Password")
	private String password ;
	@JsonProperty("UserType")
	private String userType ;
	@JsonProperty("LeadId")
	private String leadId ;
	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	@JsonProperty("ReferenceNo")
	private String referenceNo ;
	@JsonProperty("ScreenName")
	private String screenName ;
	@JsonProperty("QuoteNo")
	private String quoteNo ;
	@JsonProperty("CustomerId")
	private String customerId ;
	@JsonProperty("ProductId")
	private String productId ;
	@JsonProperty("ClientType")
	private String clientType ;
	
	
}
