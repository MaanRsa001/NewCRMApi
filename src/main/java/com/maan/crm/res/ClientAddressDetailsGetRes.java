package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientAddressDetailsGetRes {

	
	@JsonProperty("ClientRef_No")
    private String clientRefNo;  

	@JsonProperty("ClientAddressId")
    private String clientAddressId ; 
   
	@JsonProperty("AddressTypeId")
    private String addresTypeId;
    
	@JsonProperty("AddressType")
    private String    addressType ;
    
	@JsonProperty("Address")
    private String address;
    
	@JsonProperty("Location")
    private String location;
    
	@JsonProperty("Lattitude")
    private String lattitude;
    
	@JsonProperty("Logitude")
    private String Logitude;

	@JsonProperty("StateId")
    private String stateId;
    
	@JsonProperty("StateName")
    private String stateName;
    
	@JsonProperty("CityId")
    private String cityId;
    
	@JsonProperty("CityName")
    private String cityName;
    
	@JsonProperty("MobileNo")
    private String mobileNo;
    
	@JsonProperty("OtherNo")
    private String otherNo;  
    
	@JsonProperty("OtherContactDetails")
    private String otherContactDetails; 
    
	@JsonProperty("LandLine")
    private String landLine;
    
	@JsonProperty("EmailId")
    private String emailId;
   
	@JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("EntryDate")
    private Date     entryDate ;
	
	@JsonProperty("Status")
	private String     status ;
}
