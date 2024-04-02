package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuoteGridRes {

	@JsonProperty("QuoteNo")
	private String quoteNo;

	@JsonProperty("QuoteStatus")
	private String quoteStatus;
	
	@JsonProperty("ReferenceNo")
	private String referenceNo;

	@JsonProperty("QuoteStatusDesc")
	private String quoteStatusDesc;

	@JsonProperty("CreatedUsertype")
	private String createdUsertype;

	@JsonProperty("EnquiryId")
	private String enquiryId;

	@JsonProperty("LeadId")
	private String leadId;

	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("ClientName")
	private String clientName;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("UpdatedDate")
	private Date updatedDate;

	@JsonProperty("Remarks")
	private String lastRemarks;

	@JsonProperty("Policytype")
	private String policytype;

	@JsonProperty("ClassDesc")
	private String classDesc;
	
	@JsonProperty("PolicyCount")
	private String policyCount;

	
}
