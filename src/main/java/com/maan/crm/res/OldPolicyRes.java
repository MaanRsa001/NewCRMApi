package com.maan.crm.res;

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
public class OldPolicyRes {

	@JsonProperty("OldPolicyNo")
	private String oldPolicyNo;
	@JsonFormat( pattern = "dd/MM/yyyy")
	@JsonProperty("OldStartDate")
	private Date oldStartDate;
	@JsonFormat( pattern = "dd/MM/yyyy")
	@JsonProperty("OldExpiryDate")
	private Date oldExpiryDate;
	@JsonProperty("OldSumInsured")
	private String oldSumInsured;
	@JsonProperty("OldnoClaimBonus")
	private String oldnoClaimBonus;
	@JsonProperty("OldDiscountPercent")
	private String oldDiscountPercent;
	
	@JsonProperty("OldCommisBasePremium")
	private Double oldCommisBasePremium;
	
	@JsonProperty("OldOtherPremium")
	private String oldOtherPremium;

	@JsonProperty("OldTotalPremium")
	private String oldTotalPremium;
	
	@JsonProperty("OldGst")
	private String oldGst;
	
	@JsonProperty("OldTotalpremiumWithgst")
	private String oldTotalpremiumWithgst;
	
	@JsonProperty("OldPolicyAddiInfo")
	private String oldPolicyAddiInfo;
	
	@JsonFormat( pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	@JsonProperty("InsuranceId")
    private String     companyId ;
	
	@JsonProperty("BranchCode")
    private String     branchCode ;
	
	@JsonProperty("RegionCode")
    private String     regionCode ;
    
	@JsonProperty("CreatedBy")
    private String     createdBy ;
	
	@JsonProperty("UserType")
    private String     userType ;

	@JsonProperty("OldInsurer")
    private String     oldInsurer ;
	
	
	@JsonProperty("OldSource")
	private String OldSource;
	
	@JsonProperty("OldSourceId")
	private Integer OldSourceId;
	
	@JsonProperty("VehicleTransferCase")
	private String VehicleTransferCase;
	
	@JsonProperty("VehicleTransferCaseId")
	private Integer VehicleTransferCaseId;
}
