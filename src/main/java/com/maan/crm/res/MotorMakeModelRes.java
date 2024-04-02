package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MotorMakeModelRes {

	@JsonProperty("MakeId")
	private int makeId;
	
	@JsonProperty("ModelId")
	private int modelId;
	
	@JsonProperty("BodyId")
	private int bodyId;
	

	@JsonProperty("VehicleModelCode")
	private int vehicleModelCode;
	

	@JsonProperty("Status")
	private String status;

	@JsonProperty("MakeNameEn")
	private String makeNameEn;
	
	@JsonProperty("ModelNameEn")
	private String modelNameEn;
	
	@JsonProperty("BodyNameEn")
	private String bodyNameEn;

	@JsonProperty("VehManfCountry")
	private int vehManfCountry;


	@JsonProperty("VehCc")
	private int vehCc;
	

	@JsonProperty("VehWeight")
	private int vehWeight;
	

	@JsonProperty("VehFueltype")
	private int vehFueltype;
	

	@JsonProperty("MakeNameAr")
	private String makeNameAr;
	

	@JsonProperty("ModelNameAr")
	private String modelNameAr;
	

	@JsonProperty("AmendId")
	private int amendId;
	

	@JsonProperty("TplRate")
	private int tplRate;
	

	@JsonProperty("BaseRate")
	private int baseRate;
	

	@JsonProperty("NetRate")
	private int netRate;
	

	@JsonProperty("EntryDate")
	private Date entryDate;
	

	@JsonProperty("EffectiveDate")
	private Date effectiveDate;
	

	@JsonProperty("Remarks")
	private String remarks;
	

	@JsonProperty("InsCompanyId")
	private String insCompanyId;
}                                      
