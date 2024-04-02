package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyAdditionalDetailsGetAllRes {

	
	 @JsonProperty("AdditionalDetailsId") 
	 private Integer additionalDetailsId;
	 
	  @JsonProperty("PolicyId")
	  private Integer policyId;
	
	  @Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	  @JsonProperty("IssueDate")
	  private Date issueDate;
	  
	  @JsonProperty("Source")
	  private String source;
	  
	  @JsonProperty("SourceId") 
	  private Integer sourceId;
	  
	  @JsonProperty("POS")     
	  private String pOS;
	  
	  @JsonProperty("POSId")
	  private Integer pOSId;
	  
	  @JsonProperty("User")    
	  private String user;
	  
	  @JsonProperty("UserId") 
	  private Integer userId;
	  
	  @JsonProperty("Location") 
	  private String location;
	  
	  @JsonProperty("PreviousPolicyNo")
	  private String previousPolicyNo;
	  
	  @JsonProperty("PreviousInsurer") 
	  private String previousInsurer;
	  
	  @JsonProperty("PreviousInsurerId") 
	  private Integer prviousInsurerId;
	  
	  @JsonProperty("PreviousInsurancePlan")
	  private String previousInsurancePlan;
	  
	  @JsonProperty("PreviousSource") 
	  private String previousSource;
	  
	  @JsonProperty("PreviousSourceId") 
	  private Integer previousSourceId;
	  
	  @JsonProperty("PreviousPOS")  
	  private String previousPOS;
	  
	  @JsonProperty("PreviousPOSId") 
	  private Integer previousPOSId;
	  
	  @JsonProperty("CommissionGeneration")
	  private String commissionGeneration;
	  
	  @JsonProperty("CommissionGenerationId") 
	  private Integer commissionGenerationId;
	  
	  @JsonProperty("DeductibleDetails") 
	  private String deductibleDetails;
	  
	  @JsonProperty("PolicyAdditionalInformation") 
	  private String policyAdditionalInformation;
	  
	  @JsonProperty("FileType") 
	  private String fileType;
	  
	  @JsonProperty("FileTypeId") 
	  private Integer fileTypeId;
	  
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
	  private Integer policyStatusId;
	  
	  @Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	  @JsonProperty("EntryDate")
	  private Date entryDate;
}
