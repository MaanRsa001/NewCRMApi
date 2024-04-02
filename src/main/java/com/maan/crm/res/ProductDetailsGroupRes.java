package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDetailsGroupRes {

	@JsonProperty("CategoryId")
	private String categoryId ;
	
	@JsonProperty("CategoryName")
	private String categoryName ;
	
	@JsonProperty("PointsList")
	private List<String> pointsList ;
	
}
