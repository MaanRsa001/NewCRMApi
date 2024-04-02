package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDetailsRes {

	@JsonProperty("ProductId")
	private Integer productId  ;
	
	@JsonProperty("SubDetailId")
	private Integer subDetailId ;
	
	@JsonProperty("CategoryId")
	private Double categoryId ;
	
	@JsonProperty("CategoryName")
	private String categoryName;
	

	@JsonProperty("Details")
	private String details ;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EffectiveDate")
	private Date effectiveDate;
	
	@JsonProperty("Status")
	private String status;
	

	@JsonProperty("AmendId")
	private Integer amendId;
}
