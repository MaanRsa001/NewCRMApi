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
public class VehicleDetailsRes {

	@JsonProperty("VehCode")
	private String vehCode;
	
	@JsonProperty("InsuranceId")
	private String comapnyId;
	
	@JsonProperty("BranchCode")
    private String     branchCode ;
	
	@JsonProperty("RegionCode")
    private String     regionCode ;
    
	@JsonProperty("CreatedBy")
    private String     createdBy ;
	
	@JsonProperty("UserType")
    private String     userType ;
	
	@JsonProperty("VehTypeId")
	private String vehTypeId;
	
	@JsonProperty("VehType")
	private String vehType;
	
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
	
	@JsonProperty("CC")
	private String cc;
	
	@JsonProperty("ManufactureYear")
	private String manufactureYear;
	
	@JsonProperty("VehClassificationId")
	private String vehClassificationId;
	
	@JsonProperty("VehClassification")
	private String vehClassification;
	
	@JsonProperty("FueltypeId")
	private String fueltypeId;

	@JsonProperty("Fueltype")
	private String fueltype;
	
	@JsonProperty("Gvw")
	private String gvw;
	
	@JsonProperty("SeatingCapacity")
	private String seatingCapacity;
	
	@JsonProperty("EngineNo")
	private String engineNo;

	@JsonProperty("VehChassisNo")
	private String vehChassisNo;
	
	@JsonProperty("Hypothication")
	private String hypothication;

	@JsonProperty("Pos")
	private String pos;

	@JsonProperty("PosId")
	private String posId;

	@JsonProperty("VehRemarks")
	private String vehRemarks;

	@JsonProperty("VehRegnNo")
	private String vehRegnNo;
	
	@JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	@JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("VehRegnDate")
	private Date vehRegnDate;

//	@JsonProperty("RtoId")
//	private String rtoId;
//
//	@JsonProperty("Rto")
//	private String rto;
//
//	@JsonProperty("ZoneId")
//	private String zoneId;
//
//	@JsonProperty("Zone")
//	private String zone;
	
	@JsonProperty("ColorId")
	private String colorId;

	@JsonProperty("ColorVariant")
	private String colorVariant;
	
	@JsonProperty("VehRegnAddress")
	private String vehRegnAddress;

//	@JsonProperty("StateId")
//	private String stateId;
//
//	@JsonProperty("State")
//	private String state;
//	
//	@JsonProperty("CityId")
//	private String cityId;
//
//	@JsonProperty("City")
//	private String city;

	@JsonProperty("PinCode")
	private String pinCode;

	@JsonProperty("VehUserName")
	private String vehUserName;
	
	@JsonProperty("VehUserContact")
	private String vehUserContact;

//	@JsonProperty("PlateNo")
//	private String plateNo;
//
//	@JsonProperty("PlateCharId")
//	private String plateCharId;
//
//	@JsonProperty("PlateChar")
//	private String plateChar;
	
	@JsonProperty("CarryingPassengers")
	private Integer carryingPassengers;
	
}
