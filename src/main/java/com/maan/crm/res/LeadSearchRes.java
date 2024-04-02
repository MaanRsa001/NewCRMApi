package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadSearchRes {

	
	@JsonProperty("LeadId")
	private String leadId;
	
	@JsonProperty("ClientName")
	private String clientName;
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("MobileNumber")
	private String mobileNumber;
	
	@JsonProperty("CrNo")
	private String crNo;
	
	@JsonProperty("AlternativeNumber")
	private String alternativeNumber;
	
	@JsonProperty("EmailId")
	private String emailId;
	
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
	
	@JsonProperty("UserNameWithMobile")
    private String     userNameWithMobile ;
	
}
