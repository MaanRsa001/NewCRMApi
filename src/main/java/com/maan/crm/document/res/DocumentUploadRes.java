package com.maan.crm.document.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocumentUploadRes {

	@JsonProperty("Clientid")
	private String clientid;
}
