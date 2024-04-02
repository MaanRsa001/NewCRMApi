package com.maan.crm.res;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDetailsViewRes {

	@JsonProperty("CategoryName")
	private List<String> categoryName;
	@JsonProperty("Details")
	private List<String> details;

}
