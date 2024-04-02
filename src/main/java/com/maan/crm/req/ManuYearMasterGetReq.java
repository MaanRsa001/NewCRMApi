package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ManuYearMasterGetReq {

	@JsonProperty("Year")
	private int year;
	
}
