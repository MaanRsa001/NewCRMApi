package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyAddOnGetAllRes {

	@JsonProperty("addOnid")
	private int addOnid;

	@JsonProperty("policyid ")
	private int policyid;

	@JsonProperty("AddOnsOpted")
	private String addOnsOpted;

	@JsonProperty("AddOnsNotOpted")
	private String addOnsNotOpted;

	
}
