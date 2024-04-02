package com.maan.crm.res;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadSearchCountRes {

	@JsonProperty("LeadCount")
	private Long leadCount;
	
	@JsonProperty("LeadDetails")
	private  List<CrmLeadRes> leadDetails;
}
