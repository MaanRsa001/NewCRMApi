package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ViewProductRes {

	@JsonProperty("ProductId")
	private Integer productId;
	

	@JsonProperty("ProductName")
	private String productName;
	

	@JsonProperty("Price")
	private double price;
	
	@JsonProperty("Quantity")
	private Integer quantity;
	

	@JsonProperty("ManufactureYear")
	private String manufactureYear;
	

	@JsonProperty("ClassTypeId")
	private Integer classTypeId;
	

	@JsonProperty("ClassType")
	private String classType;
	

	@JsonProperty("InsCompanyId")
	private String insCompanyId;
		
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
	

	@JsonProperty("Status")
	private String status;
}
