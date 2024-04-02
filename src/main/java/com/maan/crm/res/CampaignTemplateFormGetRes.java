package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CampaignTemplateFormGetRes {

		@JsonProperty("FieldId")
		private String fieldId ;
		
		@JsonProperty("FieldName")
		private String fieldName ;
		
		@JsonProperty("DispalyName")
		private String displayName ;


}
