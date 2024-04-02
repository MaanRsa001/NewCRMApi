package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VehicleRTODropDownRes {

	@JsonProperty("RTO_CODE")
	private String rtoCode;
	@JsonProperty("RTO_DESC")
	private String rtoDesc;
	@JsonProperty("STATUS")
	private String status;
	@JsonProperty("ENTRY_DATE")
	private String entryDate;
	@JsonProperty("EFFECTIVE_DATE")
	private String effectiveDate;
	@JsonProperty("AMEND_ID")
	private String amendId;
	@JsonProperty("REMARKS")
	private String remarks;
	@JsonProperty("InsuranceId")
	private String insCompanyId;
	
}                                      
