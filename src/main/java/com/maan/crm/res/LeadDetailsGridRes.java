package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadDetailsGridRes {
    
    @JsonProperty("LeadId")
    private String leadId; 
    
    @JsonProperty("ClientRefNo")
    private String clientRefNo; 
    
    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("InsCompanyId")
    private String insCompanyId;

    @JsonProperty("BusinessType")
    private String businessType;

    @JsonProperty("BusinesstypeId")
    private Integer businesstypeId;

    @JsonProperty("ClassDesc")
    private String classDesc;

    @JsonProperty("ClassId")
    private String classId;

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

    @JsonProperty("ReferredBy")
    private String referredby;

    @JsonProperty("ReferredById")
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
    private String assigntoUserId;

    @JsonProperty("Remarks")
    private String remarks;
    
    @Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @JsonProperty("EntryDate")
    private Date entryDate;

    @JsonProperty("Status")
    private String status;
    
    @JsonProperty("BranchCode")
    private String branchCode;
    
    @JsonProperty("RegionCode")
    private String regionCode;
    
    @JsonProperty("UserType")
    private String userType;

    @JsonProperty("ClientName")
    private String clientName;
    
    @JsonProperty("OldPolicyNo")
    private String oldPolicyNo;
    
    @JsonProperty("UpdatedBy")
    private String updatedBy;
    
    @JsonProperty("UpdaterUsertype")
    private String updaterUsertype;
    
    @JsonProperty("VehChassisNo")
    private String vehChassisNo;
    
    @JsonProperty("Location")
    private String location;
    
    @JsonProperty("CoverNoteNumber")
    private String coverNoteNumber;

    @JsonProperty("ProspectOwner")
    private String prospectOwner;

    @JsonProperty("ProspectOwnerId")
    private Integer prospectOwnerId;
    
    @Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @JsonProperty("UpdatedDate")
    private Date updatedDate;
}
