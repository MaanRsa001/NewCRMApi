package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CityMasterRes {

	@JsonProperty("CityId")
	private String cityId;
	@JsonProperty("CityName")
	private String cityName;
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
	@JsonProperty("StateCode")
	private String stateCode;

	@JsonProperty("InsCompanyId")
	private String insCompanyId;
}
