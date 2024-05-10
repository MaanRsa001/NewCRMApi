package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LeadProductDetailsSaveReq {
	
	@JsonProperty("SequenceNo")
	private int sequenceNo;
	
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
    
    @JsonProperty("Description")
    private String description;

    @JsonProperty("DueDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonProperty("RateFactor")
    private String rateFactor;

    @JsonProperty("SumInsured")
    private Double sumInsured;

    @JsonProperty("Rate")
    private Double rate;

    @JsonProperty("Premium")
    private Double premium;

}
