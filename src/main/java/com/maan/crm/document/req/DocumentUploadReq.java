package com.maan.crm.document.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocumentUploadReq {
	@JsonProperty("DocumentReferenceNumber")
	private String documentRef;

	@JsonProperty("DocTypeDescription")
	private String docTypeDescription;

	@JsonProperty("DocTypeId")
	private int docTypeId;

	@JsonProperty("FileName")
	private String fileName;

	@JsonProperty("OrginalFileName")
	private String orginalFileName;

	@JsonProperty("Createdby")
	private String createdby;

	@JsonProperty("DocApplicable")
	private String docApplicable;

	@JsonProperty("DocApplicableId")
	private String docApplicableId;

	@JsonProperty("InsCompanyId")
	private String insCompanyId;

	@JsonProperty("ClientId")
	private String clientId;
	
	@JsonProperty("RequestedBy")
	private String requesteBy;
	
	@JsonProperty("UplodedBy")
	private String uploadedBy;

}
