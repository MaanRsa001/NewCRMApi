package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.req.ClientUpdateStatusReq;
import com.maan.crm.req.CrmLeadSaveReq;
import com.maan.crm.req.CrmRedirectLinkReq;
import com.maan.crm.req.LeadQuoteDetailsReq;
import com.maan.crm.req.OldPolicySaveReq;
import com.maan.crm.req.ProspectPaymentSaveReq;
import com.maan.crm.req.ProspectQuotationAddOnSaveReq;
import com.maan.crm.req.ProspectQuotationInsurerSaveReq;
import com.maan.crm.req.ProspectQuotationPolicyAccountsSaveReq;
import com.maan.crm.req.ProspectQuotationSaveReq;
import com.maan.crm.req.ProspectsQuotationOtherDetailsSaveReq;
import com.maan.crm.req.VehicleDetailsSaveReq;

import lombok.Data;

@Data
public class LeadDetailsJsonTempRes {

	@JsonProperty("LeadDetails")
	private CrmLeadRes leadDetails;
	
	@JsonProperty("OldPolicyDetails")
	private OldPolicySaveReq oldPolicyDetails;

	@JsonProperty("ProspectPayment")
	private ProspectPaymentSaveReq prospectPayment;
	
	@JsonProperty("VehicleDetails")
	private VehicleDetailsRes vehicleDetails;
}
