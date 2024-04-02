package com.maan.crm.res;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientCorContactGetAllRes {


	
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
   
	@JsonProperty("EntryDate")
    private String     entryDate ;
	
	@JsonProperty("Status")
	private String     status ;
}
