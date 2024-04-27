package com.maan.crm.req;

import java.util.Date;

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
public class CrmLeadSaveReq {

	@JsonProperty("LeadId")
	private String leadId;
	
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
	
//	@JsonFormat(pattern = "dd/MM/yyyy")
//	@JsonProperty("LeadGenDate")
	private String leadGenDate;
		
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("DueDate")
	private Date dueDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	
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
	private String assigntoGroupId;
		
	@JsonProperty("AssigntoUser")
	private String assigntoUser;
	
	@JsonProperty("AssigntoUserId")
	private String assigntoUserId; 
	
	@JsonProperty("Remarks")
	private String remarks;
	

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
	
	@JsonProperty("VehChassisNo")
	private String vehChassisNo;
	
	@JsonProperty("Location")
	private String location;
	
	// PRospect Columns
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
	
	@JsonProperty("Status")
	private String status;
	
/*	@JsonProperty("VehicleDetails")
	private VehicleDetailsSaveReq vehicle; */
}
