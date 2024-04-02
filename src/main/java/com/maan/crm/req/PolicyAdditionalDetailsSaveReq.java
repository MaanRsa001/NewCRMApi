package com.maan.crm.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyAdditionalDetailsSaveReq {

	
	
	 @JsonProperty("AdditionalDetailsId") 
	 private int additionalDetailsId;
	 
	  @JsonProperty("PolicyId")
	  private int policyId;
	
	  @JsonFormat(pattern = "dd/MM/yyyy")
	  @JsonProperty("IssueDate")
	  private Date issueDate;
	  
	  @JsonProperty("Source")
	  private String source;
	  
	  @JsonProperty("SourceId") 
	  private int sourceId;
	  
	  @JsonProperty("POS")     
	  private String pOS;
	  
	  @JsonProperty("POSId")
	  private int pOSID;
	  
	  @JsonProperty("User")    
	  private String user;
	  
	  @JsonProperty("UserId") 
	  private int userId;
	  
	  @JsonProperty("Location") 
	  private String location;
	  
	  @JsonProperty("PreviousPolicyNo")
	  private String previousPolicyNo;
	  
	  @JsonProperty("PreviousInsurer") 
	  private String previousInsurer;
	  
	  @JsonProperty("PreviousInsurerId") 
	  private int previousInsurerId;
	  
	  @JsonProperty("PreviousInsurancePlan")
	  private String previousInsurancePlan;
	  
	  @JsonProperty("PreviousSource") 
	  private String previousSource;
	  
	  @JsonProperty("PreviousSourceId") 
	  private int previousSourceId;
	  
	  @JsonProperty("PreviousPOS")  
	  private String previousPOS;
	  
	  @JsonProperty("PreviousPOSId") 
	  private int previousPOSId;
	  
	  @JsonProperty("CommissionGeneration")
	  private String commissionGeneration;
	  
	  @JsonProperty("CommissionGenerationId") 
	  private int commissionGenerationId;
	  
	  @JsonProperty("DeductibleDetails") 
	  private String deductibleDetails;
	  
	  @JsonProperty("PolicyAdditionalInformation") 
	  private String policyAdditionalInformation;
	  
	  @JsonProperty("FileType") 
	  private String fileType;
	  
	  @JsonProperty("FileTypeId") 
	  private int fileTypeId;
	  
	  @JsonProperty("ReferenceNumber") 
	  private String referenceNumber;
	  
	  @JsonProperty("OtherReferenceNumber") 
	  private String otherReferenceNumber;
	  
	  @JsonProperty("PolicyReceivedYN") 
	  private String policyReceivedYN;
	  
	  @JsonProperty("PolicyVerifiedYN") 
	  private String policyVerifiedYN;
	  
	  @JsonProperty("PolicyVerifiedBy") 
	  private String policyVerifiedBy;
	  
	  @JsonProperty("PolicyCancelledYn") 
	  private String policyCancelledYn;
	  
	  @JsonProperty("PolicyCancelledYnBy") 
	  private String policyCancelledYnBy;
	  
	  @JsonProperty("PolicyStatus") 
	  private String policyStatus;
	  
	  @JsonProperty("PolicyStatusId") 
	  private int policyStatusId;
	  
	  @JsonFormat(pattern = "dd/MM/yyyy")
	  @JsonProperty("EntryDate")
	  private Date entryDate;
}
