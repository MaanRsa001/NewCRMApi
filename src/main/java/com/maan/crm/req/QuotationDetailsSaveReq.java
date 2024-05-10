package com.maan.crm.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data	
public class QuotationDetailsSaveReq {
	
	@JsonProperty("QuoteNo")
    private String quoteNo;

    @JsonProperty("ClientId")
    private String clientId;

    @JsonProperty("LeadId")
    private Integer leadId;
    
    @JsonProperty("ProspectId")
    private Integer prospectId;
    
    @JsonProperty("PolicyTypeId")
    private Integer policyTypeId;

    @JsonProperty("PolicyType")
    private String policyType;

    @JsonProperty("ClassId")
    private Integer classId;

    @JsonProperty("ClassDesc")
    private String classDesc;

    @JsonProperty("ClientName")
    private String clientName;

    @JsonProperty("OldPolicyNo")
    private String oldPolicyNo;

    @JsonProperty("StartDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @JsonProperty("ExpiryDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date expiryDate;

    @JsonProperty("Insurer")
    private String insurer;

    @JsonProperty("InsurerBranch")
    private String insurerBranch;

    @JsonProperty("InsurancePlan")
    private String insurancePlan;

    @JsonProperty("SumInsured")
    private Double sumInsured;

    @JsonProperty("DiscountPercent")
    private Double discountPercent;

    @JsonProperty("NoClaimBonus")
    private Double noClaimBonus;

    @JsonProperty("CommissionBasePremium")
    private Double commissionBasePremium;

    @JsonProperty("OtherPremium")
    private Double otherPremium;

    @JsonProperty("GST")
    private Double gst;

    @JsonProperty("PremiumWithGST")
    private Double premiumWithGst;

    @JsonProperty("PolicyAddiInfo")
    private String policyAddiInfo;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("IssueDate")
    private Date issueDate;

    @JsonProperty("IssuedByUser")
    private String issuedByUser;

    @JsonProperty("RenewalNoticeFlagYN")
    private String renewalNoticeFlagYn;

    @JsonProperty("RecommendedFlagYN")
    private String recommendedFlagYn;
}
