package com.maan.crm.document.req;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.req.LeadProductDetailsSaveReq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProspectProductRequest {

    @JsonProperty("ProspectId")
    private String prospectId;

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

    @JsonProperty("ClassDesc")
    private String classDesc;

    @JsonProperty("PolicyType")
    private String policyType;
    
    @JsonProperty("LeadProductList")
    private List<LeadProductDetailsSaveReq> leadProductList;

    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("EntryDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date updatedDate;
}
