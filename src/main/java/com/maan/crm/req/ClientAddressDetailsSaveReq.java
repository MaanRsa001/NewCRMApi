package com.maan.crm.req;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientAddressDetailsSaveReq {

   
	@JsonProperty("ClientAddressId")
    private String clientAddressId;
	
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
	
	
	
}
