package com.maan.crm.req;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginGetReq {

	@JsonProperty("LoginId")
	private String loginId;

	@JsonProperty("AgencyCode")
	private String agencyCode;

	@JsonProperty("BranchCode")
	private String branchCode;

	@JsonProperty("InsuranceId")
	private String insCompanyId;

}
