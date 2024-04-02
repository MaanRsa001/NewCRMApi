package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDetailsGroupReq {

	@JsonProperty("ProductId")
	private String productId ;
	
	@JsonProperty("InsuranceId")
	private String insuranceId ;
}
