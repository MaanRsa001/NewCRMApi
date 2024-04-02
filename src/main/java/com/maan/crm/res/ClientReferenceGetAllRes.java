package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientReferenceGetAllRes {

		
	@JsonProperty("YourReferenceName")
    private String    yourReferenceName;
	

	@JsonProperty("YourReferenceMobile")
    private String    yourReferenceMobile;
	
	@JsonProperty("YourReferenceMailid")
    private String    yourReferenceMailid;
}
