package com.maan.crm.req;

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
public class CityMasterSaveReq {

	@JsonProperty("CityId")
	private String cityId;
	@JsonProperty("CityName")
	private String cityName;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("EffectiveDate")
	private String effectiveDate;
	@JsonProperty("Remarks")
	private String remarks;
	@JsonProperty("StateCode")
	private String stateCode;
	@JsonProperty("InsuranceId")
	private String insCompanyId;
	
	
	
}
