package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TicketMasterReq {

	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("Issue")
	private String issue;
	
	@JsonProperty("IssueId")
	private Integer issueId;
	
	@JsonProperty("Remarks")
	private String remarks;
	
}
