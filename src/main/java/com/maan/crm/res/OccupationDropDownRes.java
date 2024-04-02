package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OccupationDropDownRes {

	@JsonProperty("OccupationId")
	private Integer occupationId;
	@JsonProperty("OccupationName")
	private String occupationName;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("EntryDate")
	private String entryDate;
	@JsonProperty("EffectiveDate")
	private String effectiveDate;
	@JsonProperty("AmendId")
	private String amendId;
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("InsuranceId")
	private String insCompanyId;
}
