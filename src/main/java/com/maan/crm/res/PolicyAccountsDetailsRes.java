package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyAccountsDetailsRes {


	@JsonProperty("PolicyAccId")
	private Integer policyAccId;

	@JsonProperty("PolicyId")
	private Integer policyid;

	@JsonProperty("SumInsured")
	private Double sumInsured;

	@JsonProperty("OwnDamagePremium")
	private Double ownDamagePremium;

	@JsonProperty("DiscountPercentage")
	private Double discountPercentage;

	@JsonProperty("NoClaimBonus")
	private Double noClaimBonus;

	@JsonProperty("TotalOwnDamagePremiuma")
	private Double totalOwnDamagePremiumA;

	@JsonProperty("TotalAddOnCoverPremiumB")
	private Double totalAddOnCoverPremiumB;

	@JsonProperty("ODAddOnCommissionBasePremiumAB")
	private Double oDAddOnCommissionBasePremiumAB;

	@JsonProperty("BasicTP")
	private Double basicTP;

	@JsonProperty("OwnerDriverPAYN")
	private String ownerDriverPAYN;

	@JsonProperty("BiFuelKitYN")
	private String bifuelkitYN;

	@JsonProperty("GeographicalAreaYN")
	private String geographicalAreaYN;

	@JsonProperty("OwnerDriverPA")
	private Double ownerDriverPA;

	@JsonProperty("Bifuelkit")
	private Double bifuelkit;

	@JsonProperty("GeographicalArea")
	private Double geographicalArea;
	
	@JsonProperty("UnnamedPassengerPAYN")
	private String unnamedPassengerPAYN;

	@JsonProperty("UnnamedPassengerPA")
	private Double unnamedPassengerPA;

	@JsonProperty("UnnamedPassengerLimitPerPerson")
	private Double unnamedPassengerLimitPerPerson;

	@JsonProperty("UnnamedPassengerNoOfSeats")
	private Integer unnamedPassengerNoofSeats;

	@JsonProperty("LLtoPaidDrvEmp")
	private Double lLtoPaidDrvEmp;

	@JsonProperty("LLtoPaidDrvEmpYN")
	private String lLtoPaidDrvEmpYn;

	@JsonProperty("NoofDrvEmp")
	private Integer noofDrvEmp;

	@JsonProperty("TotalLiabilityPremiumC")
	private Double totalLiabilityPremiumC;

	@JsonProperty("TotalPremiumABC")
	private Double totalPremiumABC;

	@JsonProperty("GST")
	private Double gst;
	
	@JsonProperty("PremiumwithGST")
	private Double PremiumwithGst;
	
	@JsonProperty("CommissionBasePremium")
	private Double commissionBasePremium;
	
	@JsonProperty("PAPaidDrv")
	private Double pAPaidDrv;

	@JsonProperty("PAPaidDrvYN")
	private String pAPaidDrvYN;

	@JsonProperty("PAPaidDrvNoofSeats")
	private Integer pAPaidDrvNoofSeats;

	@JsonProperty("PAPaidDrvLimitPerPerson")
	private Double policyCancelledYnBy;

	@JsonProperty("OtherPremium")
	private Double otherPremium;

	@JsonProperty("TotalPremium")
	private Double totalPremium;

	@JsonProperty("BasicPremium")
	private Double basicPremium;
	
	@JsonProperty("RiderPremium")
	private Double riderPremium;
	
	@JsonProperty("FirstYearGST")
	private Double firstYearGST;
	
	@JsonProperty("FirstYearPremiumwithGST")
	private Double firstYearPremiumwithGST;
	
	@JsonProperty("AnnualizedPremium")
	private Double annualizedPremium;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EntryDate")
	private Date entryDate;

	@JsonProperty("Status")
	private String status;
}
