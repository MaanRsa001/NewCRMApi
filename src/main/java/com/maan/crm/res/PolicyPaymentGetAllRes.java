package com.maan.crm.res;

import java.util.Date;

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
public class PolicyPaymentGetAllRes {

	@JsonProperty("Paymentdetailsid")
	private int paymentdetailsid;

	@JsonProperty("PolicyId ")
	private int policyId;

	@JsonProperty("PaymentType")
	private String paymentType;


	@JsonProperty("PaymentTypeId ")
	private int paymentTypeId;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("PaymentDate")
	private Date paymentDate;

	@JsonProperty("PaymentRefNo")
	private String paymentRefNo;

	@JsonProperty("PolicyPaymentDetailId")
	private int policyPaymentDetailId;

	@JsonProperty("BankName")
	private String bankName;
	
	@JsonProperty("PaymentAmount")
	private String paymentAmount;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("PaymentCollectedDate")
	private Date paymentCollectedDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
	                    
}
