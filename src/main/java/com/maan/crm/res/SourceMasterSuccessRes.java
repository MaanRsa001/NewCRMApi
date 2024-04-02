package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class SourceMasterSuccessRes {
	@JsonProperty("Response")
	private String response;
	
	@JsonProperty("SourceId")
	private String sourceId;
}
