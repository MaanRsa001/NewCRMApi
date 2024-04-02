package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StateMasterDropDownRes {

	@JsonProperty("StateId")
    private String stateId; 
	
	@JsonProperty("CountryId")
    private String    countryId ;
    
    @JsonProperty("EffectiveDate")
    private String effectiveDate;
  
	@JsonProperty("AmendId")
    private String amendId ;
    
   
    @JsonProperty("StateName")
    private String    stateName ;

    @JsonProperty("Status")
    private String    status ;
    
    @JsonProperty("EntryDate")
    private String      entryDate ;
    
    @JsonProperty("StateType")
    private String     stateType;
    
    @JsonProperty("XgenCode")
    private String    xgenCode;
    
    @JsonProperty("GstYn")
    private String    gstYn;
    
    @JsonProperty("GstCode")
    private String gstCode;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	

	@JsonProperty("InsuranceId")
	private String insCompanyId;
		}

