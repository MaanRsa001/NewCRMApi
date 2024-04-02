package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginGetRes {

	@JsonProperty("LoginId")
	private String loginId;

	@JsonProperty("AgencyCode")
	private String agencyCode;

	@JsonProperty("BranchCode")
	private String branchCode;

	@JsonProperty("InsuranceId")
	private String insCompanyId;
	
	@JsonProperty("UserMail")
	private String userMail;
	
	@JsonProperty("UserType")
	private String userType;
	
	@JsonProperty("Username")
	private String userName;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("MobileNumber")
	private String mobileNumber;
	
	@JsonProperty("Password")
	private String password;

	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("Status")
	private String status;

	@JsonProperty("UnderWritterList")
	private List<UnderWritterRes> underWritterList; 
}
