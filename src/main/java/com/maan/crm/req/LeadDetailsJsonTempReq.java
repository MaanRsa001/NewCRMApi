package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LeadDetailsJsonTempReq {

	@JsonProperty("LeadDetails")
	private CrmLeadSaveReq leadDetails;
	
	@JsonProperty("OldPolicyDetails")
	private OldPolicySaveReq oldPolicyDetails;

	@JsonProperty("ProspectPayment")
	private ProspectPaymentSaveReq prospectPayment;
	
}
