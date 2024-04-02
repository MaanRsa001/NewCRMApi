/* 
*  Copyright (c) 2019. All right reserved
 * Created on 2022-05-14 ( Date ISO 2022-05-14 - Time 15:00:24 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

/*
 * Created on 2022-05-14 ( 15:00:24 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */

package com.maan.crm.req;

import java.io.Serializable;
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

public class PolicyDetailsSaveReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Policyid")
	private Integer policyid;

	@JsonProperty("LeadId")
	private Integer leadId;
	

	@JsonProperty("ProspectId")
	private Integer prospectId;
	
	@JsonProperty("BusinessType")
	private String businessType;


	@JsonProperty("InsuranceId")
	private String insCompanyId;
	

	@JsonProperty("BranchCode")
	private String branchCode;
	
	@JsonProperty("BusinessTypeId")
	private Integer businessTypeId;

	@JsonProperty("Classs")
	private String classs;

	@JsonProperty("ClassId")
	private Integer classId;

	@JsonProperty("PolicyType")
	private String policyType;

	@JsonProperty("PolicyTypeId")
	private Integer policyTypeId;

	@JsonProperty("Insurer")
	private String insurer;

	@JsonProperty("InsurerId")
	private Integer insurerId;

	@JsonProperty("InsurerBranch")
	private String insurerBranch;

	@JsonProperty("InsurerBranchId")
	private Integer insurerBranchId;

	@JsonProperty("InsurancePlan")
	private String insurancePlan;

	@JsonProperty("CoInsurerDetails")
	private String coInsurerDetails;

	@JsonProperty("VehicleId")
	private Integer vehicleId;

	@JsonProperty("ClientId")
	private Integer clientId;

	@JsonProperty("ClientRefNo")
	private String clientRefNo;

	@JsonProperty("CoverNoteNumber")
	private String coverNoteNumber;

	@JsonProperty("PolicyNumber")
	private String policyNumber;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("StartDate")
	private Date startDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("ExpiryDate")
	private Date expiryDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("NextPremiumDueDate")
	private Date nextPremiumDueDate;

	@JsonProperty("RenewableFlagYN")
	private String renewableFlagYN;

	@JsonProperty("SumInsured")
	private double sumInsured;

	@JsonProperty("DiscountPercentage")
	private double discountPercentage;

	@JsonProperty("NoClaimBonus")
	private double noClaimBonus;

	@JsonProperty("CommissionBasePremium")
	private double commissionBasePremium;

	@JsonProperty("OtherPremium")
	private double otherPremium;

	@JsonProperty("TotalPremium")
	private double totalPremium;

	@JsonProperty("Gst")
	private double GST;

	@JsonProperty("PremiumWithGST")
	private double PremiumWithGST;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("CommencementDate")
	private Date commencementDate;

	@JsonProperty("PolicyTermInYears")
	private Integer policyTermInYears;

	@JsonProperty("PremiumPayingTermInYears")
	private Integer premiumPayingTermInYears;

	@JsonProperty("AutoDebitYN")
	private String autoDebitYN;

	@JsonProperty("PremiumPaymentType")
	private String PremiumPaymentType;

	@JsonProperty("PremiumPaymentTypeId")
	private Integer premiumPaymentTypeId;

	@JsonProperty("premiumPaymentFrequency")
	private String premiumPaymentFrequency;

	@JsonProperty("premiumPaymentFrequencyId")
	private Integer premiumPaymentFrequencyId;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("PremiumDueDate")
	private Date premiumDueDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("MaturityDate")
	private Date maturityDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("Statuss")
    private String statuss ;
	
	@JsonProperty("StatusDescription")
    private String     statusDescription;


}
