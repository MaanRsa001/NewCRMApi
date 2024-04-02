package com.maan.crm.res;

import java.util.Date;

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
public class ProspectQuotationVehicleDetailsRes {

	@JsonProperty("ProspectId")
	private int prospectId;
	
	@JsonProperty("QuotationVehicleId")
	private int quotationVechid;

	@JsonProperty("MakeModelVariant")
	private String makeModelVariant;
		
	@JsonProperty("RegistrationNumber")
	private String registrationNumber;
	
	@JsonProperty("EngineNumber")
	private String engineNumber;
		
	@JsonProperty("ChassisNumber")
	private String chassisNumber;
	
	@JsonProperty("Idv")
	private String idv;
	
	@JsonProperty("ElectronicAccessoryValue")
	private String electronicAccessoryValue;
	
	@JsonProperty("OtherthanElecAccValueYn")
	private String otherthanElecValueYn;
	
	@JsonProperty("OtherthanElecAccValue")
	private Integer otherthanElecValue;
	
	
	@JsonProperty("CubicCapacity")
	private Integer cubicCapacity;
	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
}
