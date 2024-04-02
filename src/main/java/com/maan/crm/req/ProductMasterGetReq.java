package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductMasterGetReq {

	@JsonProperty("ProductId")
	private Integer productId;
	
	
}
