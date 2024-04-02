package com.maan.crm.req;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyRiderDetailsSaveReq {
   

    
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

}
