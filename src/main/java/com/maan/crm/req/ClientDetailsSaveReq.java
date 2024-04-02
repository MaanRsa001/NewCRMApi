package com.maan.crm.req;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientDetailsSaveReq {
   
	@JsonProperty("ClientRefNo")
    private String clientRefNo;  
	
	@JsonProperty("ClientTypeId")
    private String clientTypeId ;
	
	@JsonProperty("ClientType")
    private String clientType;
  	
	@JsonProperty("Title")
    private String    title;
	
	@JsonProperty("TitleId")
    private String     titleid;
    
	@JsonProperty("ClientName")
    private String    clientName ;
    
	@JsonProperty("Gender")
    private String    gender ;

	@JsonProperty("GenderId")
    private String    genderId ;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("DateOfBirth")
    private Date      dateOfBirth ;
    
	@JsonProperty("Age")
    private String  age ;
    
	@JsonProperty("MaritalStatus")
    private String     matirialStatus;
    
	@JsonProperty("MaritalStatusId")
    private String     maritalStatusid;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("DateOfAnniversary")
    private Date dateOfAnniversary ;
   
	@JsonProperty("CRNo")
    private String  crno; 

	@JsonProperty("Occupation")
    private String    occupation;
	
	@JsonProperty("OccupationId")
    private String    occupationid;
	
	@JsonProperty("Designation")
    private String designation;
	
	@JsonProperty("AnnualIncome")
    private String annualIncome;
    
	@JsonProperty("AnnualIncomeId")
    private String annualIncomeid;
	
	@JsonProperty("PassportNo" )
    private String passportNo;
	
	@JsonProperty("GstIn")
    private String gstin;
    
	@JsonProperty("DmatId")
    private String dmatid;
	
	
	
	@JsonProperty("InsuranceId")
    private String     insCompanyId ;
	
	@JsonProperty("BranchCode")
    private String     branchCode ;
	
	@JsonProperty("RegionCode")
    private String     regionCode ;
    
	@JsonProperty("CreatedBy")
    private String     createdBy ;
	
	@JsonProperty("UserType")
    private String     userType ;

	@JsonProperty("Statuss")
    private String     statuss ;
	
	@JsonProperty("StatusDescription")
    private String     statusDescription;

	@JsonProperty("MobileNumber")
	private String mobileNumber;
	
	@JsonProperty("AlternativeNumber")
	private String alternativeNumber;
	
	@JsonProperty("EmailId")
	private String emailId;
	
	@JsonProperty("ReferenceName")
    private String referenceName;
   
	@JsonProperty("ReferenceNameId")
    private String referenceNameid;
	
	@JsonProperty("ReferencerDetailsYn")
	private String referencerDetailsYn;
	
	@JsonProperty("ReferencerMobile")
	private String referencerMobile;
	

	@JsonProperty("ReferencerEmail")
	private String referencerEmail;
	
	
	@JsonProperty("Source")
    private String source;
   
	@JsonProperty("SourceId")
    private String sourceid;
	
	@JsonProperty("WillProvideReference")
    private String     willProvideReference ;
	
	@JsonProperty("WillProvideReferenceId")
    private String     willProvideRefId ;
	
	@JsonProperty("YourReferenceName")
    private String    yourReferenceName;
	

	@JsonProperty("YourReferenceMobile")
    private String    yourReferenceMobile;
	
	@JsonProperty("YourReferenceMailid")
    private String    yourReferenceMailid;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("LastVisitedDate")
    private Date   lastVisitedDate;
	
	}
