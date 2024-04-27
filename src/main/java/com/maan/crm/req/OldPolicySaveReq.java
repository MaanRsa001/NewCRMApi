package com.maan.crm.req;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

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
public class OldPolicySaveReq {

	@JsonProperty("OldPolicyNo")
	private String oldPolicyNo;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("OldStartDate")
	private Date oldStartDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("OldExpiryDate")
	private Date oldExpiryDate;
	@JsonProperty("OldSumInsured")
	private String oldSumInsured;
	@JsonProperty("OldnoClaimBonus")
	private String oldnoClaimBonus;
	@JsonProperty("OldDiscountPercent")
	private String oldDiscountPercent;
	
	@JsonProperty("OldCommisBasePremium")
	private String oldComisBasePremium;
	
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

	@JsonProperty("OldInsurer")
    private String     oldInsurer ;
	
	@JsonProperty("OldSource")
	private String oldSource;
	
	@JsonProperty("OldSourceId")
	private Integer OldSourceId;
	
	@JsonProperty("VehicleTransferCase")
	private String vehicleTransferCase;
	
	@JsonProperty("VehicleTransferCaseId")
	private Integer vehicleTransferCaseId;
}
