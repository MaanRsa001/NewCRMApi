package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EnquiryGetRes {

	@JsonProperty("ClientDetails")
	private ClientDetailsGridRes clientDetails ;
	
	@JsonProperty("LeadDetails")
	private LeadSearchRes leadDetails ;
	
	@JsonProperty("EnquiryDetails")
	private EnquiryDetailsRes enquiryDetails ;
	
	@JsonProperty("QuoteCount")
	private Long quoteCount ;
	
}
