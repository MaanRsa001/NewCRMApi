package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ClientDetailsGridRes {
	
	@JsonProperty("ClientRefNo")
    private String clientRefNo;  
	
	@JsonProperty("ClientType")
    private String clientType;
  	
	@JsonProperty("Title")
    private String    title;
	
	@JsonProperty("ClientName")
    private String    clientName ;
    
	@JsonProperty("Gender")
    private String    gender ;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("DateOfBirth")
    private Date      dateOfBirth ;
    
	@JsonProperty("Age")
    private String  age ;
    
	@JsonProperty("MaritalStatus")
    private String     matirialStatus;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("DateOfAnniversary")
    private Date dateOfAnniversary ;
   
	@JsonProperty("CRNo")
    private String  crno; 

	@JsonProperty("Occupation")
    private String    occupation;
	
	@JsonProperty("Designation")
    private String designation;
	
	@JsonProperty("AnnualIncome")
    private String annualIncome;
	
	@JsonProperty("PassportNo" )
    private String passportNo;
	
	@JsonProperty("GstIn")
    private String gstin;
    
	@JsonProperty("DmatId")
    private String dmatid;
	
	@JsonProperty("DefaultUser")
    private String defaultUser;
      
	@JsonProperty("IsGroupClient")
    private String isGroupClient;
    
	
	@JsonProperty("GroupClient")
    private String groupClient;
	
	@JsonProperty("Pos")
    private String pos;
    
	@JsonProperty("Source")
    private String source;
   
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("LastVisitedDate")
    private Date   lastVisitedDate;
	
	
	@JsonProperty("WillProvideReference")
    private String     willProvideReference ;
   
	@JsonProperty("CompanyType")
    private String     companyType ;
    
	@JsonProperty("InsuranceId")
    private String     insCompanyId ;
	
	@JsonProperty("BranchCode")
    private String     branchCode ;
	
	@JsonProperty("RegionCode")
    private String     regionCode ;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EntryDate")
    private Date  entryDate ;
	
	@JsonProperty("Status")
	private String     status ;
	
	@JsonProperty("ReferenceName")
    private String     referenceName ;
	
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
	
	@JsonProperty("YourReferenceName")
    private String    yourReferenceName;
	

	@JsonProperty("YourReferenceMobile")
    private String    yourReferenceMobile;
	
	@JsonProperty("YourReferenceMailid")
    private String    yourReferenceMailid;
	
	@JsonProperty("LeadCount")
    private String    leadCount;
	
	@JsonProperty("EnquiryCount")
    private String    enquieryCount;
	
	@JsonProperty("QuoteCount")
    private String    quoteCount;
	
	@JsonProperty("PolicyCount")
    private String    policyCount;
}
