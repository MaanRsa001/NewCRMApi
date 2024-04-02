package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDetailsSaveReq {

	@JsonProperty("ProductId")
	private Integer productId;
	
	@JsonProperty("SubDetailId")
	private String subDetailId;
	
	@JsonProperty("CategoryId")
	private String categoryId;

	@JsonProperty("CategoryName")
	private String categoryName;

	@JsonProperty("Details")
	private String details;
}
