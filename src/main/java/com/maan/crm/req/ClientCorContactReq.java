package com.maan.crm.req;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientCorContactReq {
   
  
	@JsonProperty("ContactId")
    private String contactId ;
    
   
	@JsonProperty("ContactPersonName")
    private String    contactPersonName ;

	@JsonProperty("ContactPersonDesigination")
    private String contactPersonDesigination;
    
	@JsonProperty("MobileNo")
    private String mobileNo;
    
    @Column(name="Email")
    private String email;

}
