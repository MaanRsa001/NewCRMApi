package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadQuoteDetailsReq {

	@JsonProperty("LeadId")
	private String leadId ;
	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	@JsonProperty("ReferenceNo")
	private String referenceNo ;
	
	@JsonProperty("QuoteNo")
	private String quoteNo ;
	@JsonProperty("CustomerId")
	private String customerId ;
	@JsonProperty("ProductId")
	private String productId ;
	@JsonProperty("PolicyNo")
	private String policyNo ;
	
	
 }
