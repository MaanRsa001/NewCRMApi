package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyAssuredDetailsRes {

	
	@JsonProperty("PolicyId")
    private Integer    policyId ;
	
	@JsonProperty("AssuredId")
    private Integer    assuredId ;

	@JsonProperty("Name")
    private String    name ;
	
	@JsonProperty("Relation")
    private String    relation ;
	
	@JsonProperty("RelationId")
    private Integer     relationId;
    
	@JsonProperty("Gender")
    private String   gender  ;
	
	@JsonProperty("GenderId")
    private Integer   genderId  ;

	@JsonProperty("SumAssured")
    private Double sumAssured;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("Dob")
    private Date dob  ;
   
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EntryDate")
    private Date     entryDate ;
	
	@JsonProperty("Status")
	private String     status ;
}
