package com.maan.crm.bean;

import java.io.Serializable;

import javassist.SerialVersionUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CampaignMasterId implements Serializable{

	private static final long SerialVersionUID =1L;
	
	private String campaignId;

	
}
