package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InviteMailRes {

	@JsonProperty("Response")
	private String response;
	
}
