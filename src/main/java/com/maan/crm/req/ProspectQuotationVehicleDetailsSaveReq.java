package com.maan.crm.req;

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
public class ProspectQuotationVehicleDetailsSaveReq {

	@JsonProperty("ProspectId")
	private int prospectId;
	
	@JsonProperty("QuotationVehicleId")
	private int quotationVehicleId;

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
	
	@JsonProperty("CubicCapacity")
	private String cubicCapacity;
	
	@JsonProperty("ElectronicAccessoryValue")
	private String electronicAccessoryValue;
	
	@JsonProperty("OtherthanElecAccValueYN")
	private String otherthanElecAccValueYn;
	
	@JsonProperty("OtherthanElecAccValue")
	private Integer otherthanElecAccValue;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
}
