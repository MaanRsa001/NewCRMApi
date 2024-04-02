package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ClientDetailsViewFromLeadRes {
	
	@JsonProperty("ClientRefNo")
    private String clientRefNo;  
	
	@JsonProperty("ClientType")
    private String clientType;
	
	@JsonProperty("ClientName")
    private String clientName;
 	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("DateOfBirth")
	private Date dateOfBirth;
    
	
	@JsonProperty("Age")
    private Integer  age;
    
	@JsonProperty("WillProvideReference")
    private String     willProvideReference ;
   
	// Client Address Details 
	
	@JsonProperty("Location")
    private String location;
	
	@JsonProperty("EmailId")
    private String emailId;
	
	@JsonProperty("OtherContactDetails")
	private String otherContactDetails; 
	
}
