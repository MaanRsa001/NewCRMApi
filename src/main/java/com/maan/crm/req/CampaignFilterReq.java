package com.maan.crm.req;

import java.util.Date;
import java.util.List;

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

public class CampaignFilterReq {

	@JsonProperty("OccupationIds")
	private List<Integer> occupationIds;


	@JsonProperty("AnnualIncomeIds")
	private List<Integer> annualIncomeIds;

	@JsonProperty("AgeFrom")
	private Integer ageFrom;
	

	@JsonProperty("AgeTo")
	private Integer ageTo;
	

	@JsonProperty("CampaignId")
	private String campaignId;
	
}
