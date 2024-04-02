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
public class PolicyPaymentDetailsGetReq {


	@JsonProperty("Paymentdetailsid")
	private int paymentdetailsid;

	@JsonProperty("PolicyId ")
	private int policyId;
	
	
}
