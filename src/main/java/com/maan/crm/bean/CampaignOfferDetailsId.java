package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignOfferDetailsId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String campaignId;
	
	private Integer offerId;



}
