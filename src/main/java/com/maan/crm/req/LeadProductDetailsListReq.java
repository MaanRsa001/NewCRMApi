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
    
    @JsonProperty("InsuranceId")
    private String insCompanyId;

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
