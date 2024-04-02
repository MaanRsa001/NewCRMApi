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
public class ProspectQuotationInsurerGetReq {


	
	@JsonProperty("InsurerId")
	private int insurerId;
	
	@JsonProperty("Insurer")
	private String insurer;
	
	@JsonProperty("InsurerDetailsId")
	private int insurerDetailsId;
	
	@JsonProperty("ProspectId")
	private int prospectId;
		
}
