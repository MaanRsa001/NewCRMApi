package com.maan.crm.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TrackingReq {

	
	@JsonProperty("TrackingId")
	private String trackingId;

	@JsonProperty("RegionCode")
	private String regionCode;
	
	@JsonProperty("BranchCode")
	private String branchCode;

	@JsonProperty("InsuranceId")
	private String insCompanyId;

	
	@JsonProperty("Status")
	private String status;

	@JsonProperty("StatusDescription")
	private String statusDescription;
	
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("ClientName")
	private String clientName;


	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	

	@JsonProperty("LeadId")
	private String leadId;
	

	@JsonProperty("ProspectId")
	private String prospectId;
	

	@JsonProperty("PolicyId")
	private String policyId;
}
