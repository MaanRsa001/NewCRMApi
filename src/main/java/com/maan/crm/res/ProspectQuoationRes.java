package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProspectQuoationRes {

	@JsonProperty("QuotationId")
	private String quotationId;
		
	@JsonProperty("ProspectId")
	private String prospectId;
	
	@JsonProperty("Classs")
	private String classs;

	@JsonProperty("PolicyType")
	private String policyType;
		
	@JsonProperty("ClientName")
	private String clientName;
	
	@JsonProperty("OldPolicyNo")
	private String oldPolicyNo;
		
	@JsonProperty("PremiumCoverType")
	private String premiumCoverType;
	
	@JsonProperty("PremiumCoverTypeId")
	private String premiumCoverTypeId;
		
	@JsonProperty("PolicyTerm")
	private String policyTerm;
	
	@JsonProperty("PolicyTermId")
	private String policyTermId;

	@JsonProperty("StartDate")
	private String startDate;
		
	@JsonProperty("ExpiryDate")
	private String expiryDate;
	
	@JsonProperty("EntryDate")
	private String entryDate;


}
