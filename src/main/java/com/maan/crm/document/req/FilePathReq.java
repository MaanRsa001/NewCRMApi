package com.maan.crm.document.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FilePathReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	@JsonProperty("DocumentReferenceNumber")
	private String reqrefno;
	@JsonProperty("DocumentTypeId")
	private String doctypeid;
}
