package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VehColorGetReq {

	@JsonProperty("ColorId")
	private int colorId;
	
}
