package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientDetailsGetRes {

	
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
    private String    genderid ;
	
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
	
	@JsonProperty("DefaultUser")
    private String defaultUser;
    
	@JsonProperty("DefaultUserId")
    private String defaultUserid;
    
	@JsonProperty("IsGroupClient")
    private String isGroupClient;
    
	@JsonProperty("IsGroupClientId")
    private String isGroupClientid;
   
	@JsonProperty("GroupClient")
    private String groupClient;
	
	@JsonProperty("GroupClientId")
    private String groupClientId;
    
	@JsonProperty("Pos")
    private String pos;
    
	@JsonProperty("PosId")
    private String posId;
    
	@JsonProperty("Source")
    private String source;
   
	@JsonProperty("SourceId")
    private String sourceid;
	
	@JsonProperty("WillProvideReferenceId")
    private String     willProvideRefId ;
	
	@JsonProperty("WillProvideReference")
    private String     willProvideReference ;
   
	@JsonProperty("CompanyType")
    private String     companyType ;
    
	@JsonProperty("CompanyTypeId")
    private String     companyTypeId ;
    
	@JsonProperty("InsuranceId")
    private String     insCompanyId ;
	
	@JsonProperty("BranchCode")
    private String     branchCode ;
	
	@JsonProperty("RegionCode")
    private String     regionCode ;
   
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EntryDate")
    private Date     entryDate ;
	
	@JsonProperty("Status")
	private String     status ;
	
	@JsonProperty("CreatedBy")
    private String     createdBy ;
	
	@JsonProperty("UserType")
    private String     userType ;
	
	@JsonProperty("ReferenceName")
    private String     referenceName ;
	
	@JsonProperty("ReferenceNameId")
    private String     referenceNameId ;

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
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("LastVisitedDate")
    private Date   lastVisitedDate;
	
}
