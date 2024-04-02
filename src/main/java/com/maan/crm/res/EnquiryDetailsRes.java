package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EnquiryDetailsRes {

	@JsonProperty("SubStatusCode")
	private String enqStatus;

	@JsonProperty("SubStatusDesc")
	private String enqStatusDesc;

	@JsonProperty("UserType")
	private String createdUsertype;

	@JsonProperty("LeadId")
	private String leadId;

	@JsonProperty("EnquiryId")
	private String enquiryId;

	@JsonProperty("ClientRefNo")
	private String clientRefNo;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("AllotedUW")
	private String allotedUw;

	@JsonProperty("AlloterUWId")
	private String allotedUwId;

	@JsonProperty("PremiumRate")
	private String premiumRate;

	@JsonProperty("SuggestedPrmium")
	private String suggestPremium;

	@JsonProperty("SumInsured")
	private String sumInsured;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("UpdatedDate")
	private Date updatedDate;

	@JsonProperty("Remarks")
	private String lastRemarks;

	@JsonProperty("ClassDesc")
	private String classDesc;

	@JsonProperty("PolicyType")
	private String policyType;

	@JsonProperty("EnquiryDesc")
	private String enquiryDescription;

	@JsonProperty("BusinessType")
	private String businessType;

	@JsonProperty("BusinessTypeId")
	private String businessTypeId;

	@JsonProperty("ClassId")
	private String classId;

	@JsonProperty("PolicyTypeId")
	private String policytypeId;

	@JsonProperty("PolicyType")
	private String policytype;

	@JsonProperty("EnqPremiumRate")
	private String enqPremiumRate;

	@JsonProperty("UpdatedBy")
	private String updatedBy;

	@JsonProperty("UpdaterUsertype")
	private String updaterUsertype;

	@JsonProperty("InsuranceId")
	private String insCompanyId;

	@JsonProperty("BranchCode")
	private String branchCode;

	@JsonProperty("BroughtBy")
	private String broughtBy;

	@JsonProperty("BroughtCode")
	private String broughtCode;

	@JsonProperty("BroughtName")
	private String broughtName;
}
