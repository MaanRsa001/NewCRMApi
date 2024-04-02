package com.maan.crm.req;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class StateMasterSaveReq {
	
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
		
}
