package com.maan.crm.req;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDataReq {
	
	@JsonProperty("PolicyId")
	private String policyId;
	
	@JsonProperty("PolicyNumber")
	private String policyNumber;
	
	@JsonProperty("ProspectId")
	private int prospectId;
	
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
    
    @JsonProperty("Classs")
    private String classs;
    
    @JsonProperty("PolicyType")
    private String policyType;
    
    @JsonProperty("LeadProductList")
    private List<LeadProductDetailsSaveReq> leadProdutList;
    
    @JsonProperty("Insurer")
    private String insurer;
    
    @JsonProperty("InsurerBranch")
    private String insurerBranch;
    
    @JsonProperty("InsurancePlan")
    private String insurancePlan;
    
    @JsonProperty("CoInsurerYN")
    private String coInsurerYN;
    
    @JsonProperty("CoInsurerName")
    private String coInsurerName;
    
    @JsonProperty("ReinsurerYN")
    private String reinsurerYN;
    
    @JsonProperty("ReinsurerName")
    private String reinsurerName;
    
    @JsonProperty("VehicleRegNo")
    private String vehicleRegNo;
    
    @JsonProperty("CoverNoteNo")
    private Integer coverNoteNo;
    
    @JsonProperty("StartDate")
    private String startDate;
    
    @JsonProperty("ExpiryDate")
    private String expiryDate;
    
    @JsonProperty("NextPremiumDueDate")
    private String nextPremiumDueDate;
    
    @JsonProperty("RenewableFlagYN")
    private String renewableFlagYN;
    
    @JsonProperty("SumInsured")
    private Double sumInsured;
    
    @JsonProperty("OtherPremium")
    private Double otherPremium;
    
    @JsonProperty("FinancialCharges")
    private Double financialCharges;
    
    @JsonProperty("InstallmentYN")
    private String installmentYN;
    
    @JsonProperty("NoOfInstallments")
    private Integer noOfInstallments;
    
    @JsonProperty("DeductibleAmount")
    private Double deductibleAmount;
    
    @JsonProperty("DeductiblePercent")
    private Double deductiblePercent;
    
    @JsonProperty("Describtion")
    private String describtion;
    
    @JsonProperty("TotalPremium")
    private Double totalPremium;
    
    @JsonProperty("PremiumPaymentType")
    private String premiumPaymentType;
    
    @JsonProperty("PaymentRefNo")
    private String paymentRefNo;
    
    @JsonProperty("PaymentDate")
    private String paymentDate;
    
    @JsonProperty("BankName")
    private String bankName;
    
    @JsonProperty("PaymentAmount")
    private Double paymentAmount;
    
    @JsonProperty("PaymentCollectedDate")
    private String paymentCollectedDate;
    
    @JsonProperty("PaymentRemarks")
    private String paymentRemarks;
    
    @JsonProperty("PremiumDueDate")
    private String premiumDueDate;
    
    @JsonProperty("EntryDate")
    private String entryDate;
    
    @JsonProperty("CreatedBy")
    private String createdBy;
    
    @JsonProperty("Status")
    private String status;
    

    
    @JsonProperty("Regioncode")
    private String regionCode;
    
    @JsonProperty("BranchCode")
    private String branchCode;
}
