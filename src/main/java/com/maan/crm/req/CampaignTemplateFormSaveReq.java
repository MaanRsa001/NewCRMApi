package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CampaignTemplateFormSaveReq {

	@JsonProperty("FieldId")
	private String fieldId ;
	
	@JsonProperty("FieldName")
	private String fieldName ;
	
	@JsonProperty("DispalyName")
	private String displayName ;
}
