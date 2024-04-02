package com.maan.crm.req;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductMasterListSaveReq {

	@JsonProperty("ProductId")
	private String productId;

	@JsonProperty("ProductName")
	private String productName;
	
	@JsonProperty("InsuranceId")
	private String insCompanyId;

	@JsonFormat(pattern = "dd/MM/YYYY")
	@JsonProperty("EffectiveDate")
	private Date effectiveDate;

	@JsonProperty("Price")
	private String price;

	@JsonProperty("Quantity")
	private String quantity;

	@JsonProperty("Category")
	private String category;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("ManufactureYear")
	private String manufactureYear;

	@JsonProperty("ClassTypeId")
	private String classTypeId;

	@JsonProperty("ClassType")
	private String classType;

	@JsonProperty("ProductDetails")
	private List<ProductDetailsSaveReq> productDetailsSaveList;

}
