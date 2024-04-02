package com.maan.crm.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyRiderDetailsRes {

	
	@JsonProperty("RiderId")
    private int riderId;  
	
	@JsonProperty("PolicyId")
    private int    policyId ;
    

	@JsonProperty("RiderName")
    private String riderName ;
    
	@JsonProperty("SumAssured")
    private double sumAssured;
    
	@JsonProperty("Premium")
    private double    premium ;
	
   @JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("EntryDate")
    private Date     entryDate ;
	
	@JsonProperty("Status")
	private String     status ;
}
