package com.maan.crm.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyAddOnSaveReq {

	@JsonProperty("addOnid")
	private int addOnid;

	@JsonProperty("policyid ")
	private int policyid;

	@JsonProperty("AddOnsOpted")
	private String addOnsOpted;

	@JsonProperty("AddOnsNotOpted")
	private String addOnsNotOpted;

	
}
