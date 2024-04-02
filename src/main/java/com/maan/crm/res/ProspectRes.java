package com.maan.crm.res;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProspectRes  implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ProspectQuotationInsurer")
	private ProspectQuotationInsurerRes insurerDetails;
	
	@JsonProperty("ProspectPayment")
	private ProspectPaymentRes paymentRes;
	
	@JsonProperty("ProspectQuotationBasicInfo")
	private ProspectQuoationRes quotationBasic;

	@JsonProperty("ProspectQuotationVehicle")
	private ProspectQuotationVehicleDetailsRes vehicleDetails;
	
	@JsonProperty("ProspectDetails")
	private ProspectDetailsRes prospectDetails;
	

	@JsonProperty("ProspectOldPolicy")
	private ProspectOldPolicyDetailsRes prospectoldPolicy;
	

	@JsonProperty("ProspectQuotationAddOn")
	private ProspectQuotationAddOnRes prospectQuotationAddOn;


	@JsonProperty("ProspectQuotationPolicyAccount")
	private ProspectQuotationPolicyAccountsRes prospectQuotationPolicyAccount;


	@JsonProperty("ProspectQuotationOtherDetails")
	private ProspectsQuotationOtherDetailsRes prospectQuotationOtherDetail;
}
