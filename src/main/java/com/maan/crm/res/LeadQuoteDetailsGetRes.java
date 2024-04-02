package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadQuoteDetailsGetRes {

	@JsonProperty("LeadId")
	private Integer leadId ;
	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	@JsonProperty("ReferenceNo")
	private String referenceNo ;
	
	@JsonProperty("QuoteNo")
	private Integer quoteNo ;
	

	@JsonProperty("PolicyNo")
	private String policyNo ;
	
	@JsonProperty("TotalPremium")
	private double totalRemium;
	
	/*
	@JsonProperty("CustomerId")
	private String customerId ;
	*/
	@JsonProperty("ProductId")
	private String productId ;
	

	@JsonProperty("Status")
	private String status;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EntryDate")
	private Date entryDate;
 }
