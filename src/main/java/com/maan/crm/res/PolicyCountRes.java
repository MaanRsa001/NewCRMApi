package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyCountRes {

	@JsonProperty ("PolicyNo")
		private Integer PolicyNo;
		
		@JsonProperty("ClientRefNo")
		private String clientRefNo;

		@JsonProperty("BusinessType")
		private String businessType;
			
		@JsonProperty("BusinessTypeId")
		private Integer businessTypeId;
		
		@JsonProperty("ClassDesc")
		private String classDesc;
			
		@JsonProperty("ClassId")
		private Integer classId;
		
		@JsonProperty("PolicyType")
		private String policyType;
			
		@JsonProperty("PolicyTypeId")
		private Integer policyTypeId;
		
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@JsonProperty("LeadGenDate")
		private Date leadGenDate;
			
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@JsonProperty("DueDate")
		private Date dueDate;
		
		@JsonProperty("BrokenPolicy")
		private String brokenPolicy;
		
		@JsonProperty("Classification")
		private String classification;
			
		@JsonProperty("ClassificationId")
		private Integer classificationId;

		@JsonProperty("Source")
		private String source;
		
		@JsonProperty("SourceId")
		private Integer sourceId;
		
		@JsonProperty("Referredby")
		private String referredby;
			
		@JsonProperty("ReferredbyId")
		private Integer referredbyId;

		@JsonProperty("Othertype")
		private String othertype;
		
		@JsonProperty("OthertypeId")
		private Integer othertypeId;
		
		@JsonProperty("Pos")
		private String pos;
			
		@JsonProperty("PosId")
		private Integer posId;

		@JsonProperty("ReferenceName")
		private String referenceName;
		
		@JsonProperty("AssigntoGroup")
		private String assigntoGroup;
		
		@JsonProperty("AssigntoGroupId")
		private Integer assigntoGroupId;
			
		@JsonProperty("AssigntoUser")
		private String assigntoUser;
		
		@JsonProperty("AssigntoUserId")
		private Integer assigntoUserId;
		
		@JsonProperty("Remarks")
		private String remarks;
		
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@JsonProperty("EntryDate")
		private Date entryDate;
		
		@JsonProperty("Status")
		private String status;
		
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
		
		@JsonProperty("ProspectCount")
		private String prospectCount;
		  
		// Client Details
		
		@JsonProperty("ClientTypeId")
	    private Integer clientTypeId;
		
		@JsonProperty("ClientName")
	    private String clientName;
	 	
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@JsonProperty("DateOfBirth")
		private Date dateOfBirth;
	    
		@JsonProperty("IsGroupClientId")
		private Integer isGroupClientId;
		
		@JsonProperty("Age")
	    private String  age;
	    
		@JsonProperty("UserNameWithMobile")
	    private String  userNameWithMobile;
		
		@JsonProperty("WillProvideRefId")
	    private Integer willProvideRefId ;
	   
		@JsonProperty("MobileNumber")
		private String mobileNumber;

		@JsonProperty("AlternativeNumber")
		private String alternativeNumber;

		@JsonProperty("EmailId")
		private String emailId;
		
		// Client Address Details 
		
		@JsonProperty("Location")
	    private String location;
		/*
		@JsonProperty("EmailId")
	    private String emailId;
		*/
		@JsonProperty("OtherContactDetails")
		private String otherContactDetails; 
		
		@JsonProperty("OldPolicyNo")
		private String oldPolicyNo;
		

		
		@JsonProperty("VehChassisNo")
		private String vehChassisNo;
		
		@JsonProperty("CoverNoteNumber")
		private String coverNoteNumber;
		@JsonProperty("ProspectOwner")
		private String prospectOwner;
		@JsonProperty("ProspectOwnerId")
		private Integer prospectOwnerId;
		@JsonProperty("ProsStatusId")
		private Integer prosStatusId;
		@JsonProperty("ProsStatusDesc")
		private String prosStatusDesc;
		
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@JsonProperty("UpdatedDate")
		private Date updatedDate;
		
		@JsonFormat(pattern = "dd/MM/yyyy")
		@JsonProperty("LastVisitedDate")
	    private Date   lastVisitedDate;
	/*	@JsonProperty("OldPolicyDetails")
		private OldPolicyRes oldPolicyDetails;
		
		
		@JsonProperty("VehicleDetails")
		private VehicleDetailsRes vehicleDetails; */
		
		

		
}
