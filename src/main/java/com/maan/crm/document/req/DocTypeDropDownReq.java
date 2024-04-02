package com.maan.crm.document.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocTypeDropDownReq {

	@JsonProperty("InsuranceId")
	private String insId ;
	@JsonProperty("DocApplicableId")
	private String docApplicableId ;
	@JsonProperty("DocApplicable")
	private String docApplicable ;
}
