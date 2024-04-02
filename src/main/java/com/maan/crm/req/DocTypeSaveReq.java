package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocTypeSaveReq {

	@JsonProperty("DocApplicableId")
	private String docApplicableId ;
	@JsonProperty("DocApplicable")
	private String docApplicable ;
	@JsonProperty("DocTypeDescription")
	private String docTypeDescription;
	@JsonProperty("DocTypeId")
	private String docTypeId;
	@JsonProperty("DocRefNo")
	private String refNo ;
	
	@JsonProperty("StatusCode")
	private String docStatusCode;
	
	@JsonProperty("StatusDesc")
	private String docStatusDesc;
}
