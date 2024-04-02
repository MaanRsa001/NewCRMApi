package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class TicketMasterRes {

	@JsonProperty("TicketId")
	private String ticketId;
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("ClientName")
	private String clientName;
	
	@JsonProperty("BranchCode")
	private String branchCode;

	@JsonProperty("RegionCode")
	private String regionCode;

	@JsonProperty("MobileNumber")
	private String mobileNumber;

	@JsonProperty("EmailId")
	private String emailId;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("InsCompanyId")
	private String insCompanyId;
	
	@JsonProperty("Issue")
	private String issue;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("IssueId")
	private Integer issueId;
	
	}
