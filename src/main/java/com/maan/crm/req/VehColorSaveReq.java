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
public class VehColorSaveReq {

	@JsonProperty("VehChassisNo")
	private String vehChassisNo;
	
	@JsonProperty("InsuranceId")
	private String comapnyId;
	
	@JsonProperty("VehicleRegNo")
	private String vehicleRegNo;
	
	@JsonProperty("VehMakeId")
	private String vehMakeId;
	
	@JsonProperty("VehMake")
	private String vehMake;
	
	@JsonProperty("VehModelId")
	private String vehModelId;
	
	@JsonProperty("VehModel")
	private String vehModel;

	@JsonProperty("VehBodytypeId")
	private String vehBodytypeId;

	@JsonProperty("VehBodytype")
	private String vehBodytype;

	@JsonProperty("ColorId")
	private String colorId;

	@JsonProperty("ColorVariant")
	private String colorVariant;

	@JsonProperty("FueltypeId")
	private String fueltypeId;

	@JsonProperty("Fueltype")
	private String fueltype;

	@JsonProperty("CC")
	private String cc;

	@JsonProperty("SeatingCapacity")
	private String seatingCapacity;

	@JsonProperty("ManufactureYear")
	private String manufactureYear;

	@JsonProperty("EngineNo")
	private String engineNo;

	@JsonProperty("VehRemarks")
	private String vehRemarks;

	@JsonProperty("PlateNo")
	private String plateNo;

	@JsonProperty("PlateCharId")
	private String plateCharId;

	@JsonProperty("PlateChar")
	private String plateChar;
	
	@JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("VehicleRegnDate")
	private Date vehicleRegnDate;

	
	@JsonProperty("BranchCode")
    private String     branchCode ;
	
	@JsonProperty("RegionCode")
    private String     regionCode ;
    
	@JsonProperty("CreatedBy")
    private String     createdBy ;
	
	@JsonProperty("UserType")
    private String     userType ;

}
