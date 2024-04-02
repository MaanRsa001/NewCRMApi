package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CrmLeadSuccessRes {
	@JsonProperty("Response")
	private String response;
	
	@JsonProperty("LeadId")
	private String leadid;
	
	@JsonProperty("EncodedValues")
	private String encodedValues;
}

