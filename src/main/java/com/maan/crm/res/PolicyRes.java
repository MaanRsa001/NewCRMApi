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

public class PolicyRes  implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("PolicyDetails")
	private PolicyDetailsRes policyDetails;
	
	@JsonProperty("PolicyAddOn")
	private PolicyAddOnRes policyAddOn;
	
	@JsonProperty("PolicyPaymentDetails")
	private PolicyPaymentDetailsRes policyPaymentDetails;

	@JsonProperty("PolicyNomineeDetails")
	private PolicyNomineeDetailsRes policyNomineeDetails;
	
	@JsonProperty("PolicyAdditionalDetails")
	private PolicyAdditionalDetailsRes policyAdditionalDetails;
	
	@JsonProperty("PolicyRiderDetails")
	private PolicyRiderDetailsRes policyRiderDetails;

	@JsonProperty("PolicyAccountDetails")
	private PolicyAccountsDetailsRes policyAccountDetails;
	
	@JsonProperty("PolicyAssuredDetails")
	private PolicyAssuredDetailsRes policyAssuredDetails;
	
}
