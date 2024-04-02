package com.maan.crm.req;

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
public class CampaignDetailsSaveReq {

	@JsonProperty("ClientName")
	private String clientName;
	
	@JsonProperty("MobileNumber")
	private String mobileNumber;
	
	@JsonProperty("EmailId")
	private String emailId;
	
	@JsonProperty("Occupation")
	private String occupation;
		
	@JsonProperty("Crno")
	private String crno;
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("LeadId")
	private String leadId;
	
	@JsonProperty("CampaignId")
	private String campaignId;
	
	@JsonProperty("ClientType")
	private String clientType;
	
	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("ClientTypeId")
	private Integer clientTypeId;
	
	@JsonProperty("TitleId")
	private String titleId;
}
