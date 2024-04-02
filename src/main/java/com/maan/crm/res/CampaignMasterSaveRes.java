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
public class CampaignMasterSaveRes {

	@JsonProperty("Response")
	private String response;
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("LeadId")
	private String leadId;
	
	@JsonProperty("CampaignCode")
	private String campaigncode;
	
	
}
