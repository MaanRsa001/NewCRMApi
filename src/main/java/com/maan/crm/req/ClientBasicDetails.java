package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientBasicDetails {

	@JsonProperty("LoginId")
	private String loginId ;
	@JsonProperty("UserId")
	private String userId ;
	@JsonProperty("LoginType")
	private String loginType ;
	@JsonProperty("BranchCode")
	private String branchCode ;
	
	@JsonProperty("Password")
	private String password ;
	@JsonProperty("ClientRefNo")
	private String clientRefNo ;
	@JsonProperty("LeadId")
	private String leadId ;
	
}
