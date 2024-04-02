package com.maan.crm.document.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetDocListReq {
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
    @JsonProperty("InsCompanyId")
    private String insCompanyId ;
	
	@JsonProperty("DocApplicable")
	private String docApplicable;
	
	@JsonProperty("DocApplicableId")
	private String docApplicableId;
}
