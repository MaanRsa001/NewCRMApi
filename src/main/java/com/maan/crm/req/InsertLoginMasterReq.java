package com.maan.crm.req;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InsertLoginMasterReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("LoginId")
	private String loginId;

	@JsonProperty("CompanyId")
	private String companyId;

	@JsonProperty("Password")
	private String password;

	@JsonProperty("Username")
	private String username;

	@JsonProperty("UserMail")
	private String userMail;
	
	@JsonProperty("AgencyCode")
	private String agencyCode;

	@JsonProperty("MobileNumber")
	private String mobileNumber;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("Remarks")
	private String remarks;

	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("UserType")
	private String userType;
	
	@JsonProperty("BranchCode")
	private List<String> branchCode;
	
	
	@JsonProperty("UnderWritterList")
	private List<UnderWritterDetailsReq> underWritterList;
	

}
