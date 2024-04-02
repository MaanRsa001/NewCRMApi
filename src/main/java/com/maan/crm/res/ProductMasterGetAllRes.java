package com.maan.crm.res;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductMasterGetAllRes  implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ProspectId")
	private Integer prospectId;
	

	@JsonProperty("ProspectName")
	private String prospectName;
	

	@JsonProperty("Price")
	private double price;
	
	@JsonProperty("Quantity")
	private Integer quantity;
	

	@JsonProperty("Category")
	private String category;
	

	@JsonProperty("Description")
	private String description;
	

	@JsonProperty("ManufactureYear")
	private String manufactureYear;
	

	@JsonProperty("ClassTypeId")
	private Integer classTypeId;
	

	@JsonProperty("ClassType")
	private String classType;
	

	@JsonProperty("InsCompanyId")
	private String insCompanyId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EffectiveDate")
	private Date effectiveDate;
	

	@JsonProperty("AmendId")
	private Integer amendId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
	

	@JsonProperty("Status")
	private String status;
	
	}
