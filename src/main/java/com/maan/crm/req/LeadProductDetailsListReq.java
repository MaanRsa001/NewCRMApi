package com.maan.crm.req;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadProductDetailsListReq {
	
	@JsonProperty("LeadId")
    private String leadId;

    @JsonProperty("ClientRefNo")
    private String clientRefNo;

    @JsonProperty("ClientName")
    private String clientName;
    
    @JsonProperty("InsCompanyId")
    private String insCompanyId;
	
    @JsonProperty("BusinessType")
    private String businessType;

    @JsonProperty("BusinessTypeId")
    private Integer businessTypeId;

    @JsonProperty("ClassDescription")
    private String classDescription;

    @JsonProperty("ClassId")
    private String classId;

    @JsonProperty("PolicyType")
    private String policyType;

    @JsonProperty("PolicyTypeId")
    private Integer policyTypeId;

    @JsonProperty("Source")
    private String source;

    @JsonProperty("SourceId")
    private Integer sourceId;

    @JsonProperty("LeadGenDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date leadGenDate;

    @JsonProperty("AssignToGroup")
    private String assignToGroup;

    @JsonProperty("AssignToGroupId")
    private Integer assignToGroupId;

    @JsonProperty("AssignToUser")
    private String assignToUser;

    @JsonProperty("AssignToUserId")
    private String assignToUserId;

    @JsonProperty("DueDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;
    
    @JsonProperty("LeadProductList")
    private List<LeadProductDetailsSaveReq> leadProdutList;
    
    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("EntryDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date entryDate;

    @JsonProperty("Remarks")
    private String remarks;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("BranchCode")
    private String branchCode;

    @JsonProperty("RegionCode")
    private String regionCode;

    @JsonProperty("UpdatedBy")
    private String updatedBy;

    @JsonProperty("UpdatedDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updatedDate;
}
