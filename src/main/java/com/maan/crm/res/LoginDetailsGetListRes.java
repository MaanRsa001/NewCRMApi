package com.maan.crm.res;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginDetailsGetListRes {

	@JsonProperty("LoginId")
	private String loginId;
	
	@JsonProperty("CompanyId")
	private String companyId;

	@JsonProperty("AgencyCode")
	private String agencyCode;

	@JsonProperty("BranchCode")
	private String branchCode;

	@JsonProperty("Username")
    private String     username ;

	@JsonFormat(pattern="dd/mm/yyyy")
	@JsonProperty("Passdate")
	private Date passdate;

	@JsonProperty("CreatedBy")
    private String     createdBy ;

	@JsonProperty("Remarks")
    private String     remarks ;

	@JsonProperty("Status")
	private String status;

	@JsonProperty("MenuId")
    private String     menuId ;

	@JsonFormat(pattern="dd/mm/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;

	@JsonProperty("CoreLoginId")
	private String coreLoginId;

	@JsonProperty("AppId")
	private String appId;

	@JsonProperty("PwdCount")
	private String pwdCount;

	@JsonProperty("UserMail")
	private String userMail;

	@JsonProperty("MobileNumber")
    private String     mobileNumber ;
	
	@JsonFormat(pattern="dd/mm/yyyy")
	@JsonProperty("LastModifiedDate")
	private Date lastModifiedDate;

	@JsonProperty("LastModifiedBY")
	private String lastModifiedBy;
}
