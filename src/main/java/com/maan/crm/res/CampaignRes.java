package com.maan.crm.res;

import java.util.Date;
import java.util.List;

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
public class CampaignRes {



	@JsonProperty("ClientCount")
	private Long clientCount;
	
	@JsonProperty("CampaignRes")
	private List<CampaignCountRes> campaignRes;
	
}
