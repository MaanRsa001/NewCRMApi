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
public class VehicleDetailsGetAllReq {

	@JsonProperty("InsuranceId")
	private String insId;
	
	@JsonProperty("BranchCode")
	private String branchCode;
	
	@JsonProperty("Limit")
	private String limit;
	
	@JsonProperty("Offset")
	private String offset;
		
}
