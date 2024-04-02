package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TrackingGetallRes {

	@JsonProperty("TrackingId")
	private String trackingId;
	
	@JsonProperty("InsCompanyId")
	private String insCompanyId;
	
	@JsonProperty("BranchCode")
	private String branchCode;
	
	@JsonProperty("RegionCode")
	private String regionCode;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("StatusDescription")
	private String statusDescription;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "dd/MM/yyyy", locale ="en-IN", timezone= "Asia/Calcutta")
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
