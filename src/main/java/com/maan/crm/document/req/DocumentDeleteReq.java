package com.maan.crm.document.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocumentDeleteReq {


	@JsonProperty("ClientRefNo")
	private String clientId;

	@JsonProperty("DocTypeDescription")
	private String docTypeDescription;
	
	@JsonProperty("DocTypeId")
	private int docTypeId;
	
	@JsonProperty("DocumentRef")
	private int documentRef;
	
	@JsonProperty("DocApplicable")
	private String docApplicable;

	@JsonProperty("DocApplicableId")
	private String docApplicableId;

	@JsonProperty("InsuranceId")
	private String insCompanyId;

	
	
}
