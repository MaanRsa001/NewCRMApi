package com.maan.crm.req;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StateMasterGetReq {

	@JsonProperty("StateId")
	private String stateId;
	
	@JsonProperty("InsuranceId")
	private String insCompanyId;
}