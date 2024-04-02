package com.maan.crm.req;

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
public class ProspectQuotationInsurerSaveReq {

	@JsonProperty("ProspectId")
	private int prospectId;
	
	@JsonProperty("InsurerDetailsId")
	private int insurerDetailsId;

	@JsonProperty("Insurer")
	private String insurer;
		
	@JsonProperty("InsurerId")
	private int insurerId;
	
	@JsonProperty("InsurerBranch")
	private String insurerBranch;
		
	@JsonProperty("InsurerBranchId")
	private int insurerBranchId;
	
	@JsonProperty("InsurancePlan")
	private String insurancePlan;
		
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
}
