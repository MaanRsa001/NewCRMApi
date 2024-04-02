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
public class ProspectQuotationInsurerRes {

	@JsonProperty("ProspectId")
	private String prospectId;
	
	@JsonProperty("InsurerDetailsId")
	private String insurerDetailsId;

	@JsonProperty("Insurer")
	private String insurer;
		
	@JsonProperty("InsurerId")
	private String insurerId;
	
	@JsonProperty("InsurerBranch")
	private String insurerBranch;
		
	@JsonProperty("InsurerBranchId")
	private String insurerBranchId;
	
	@JsonProperty("InsurancePlan")
	private String insurancePlan;
		
	@JsonProperty("EntryDate")
	private String entryDate;

	
}
