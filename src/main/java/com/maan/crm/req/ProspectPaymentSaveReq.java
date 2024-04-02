package com.maan.crm.req;

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
public class ProspectPaymentSaveReq {

	@JsonProperty("PaymentDetailsId")
	private int paymentDetailsId;
	
	
	@JsonProperty("PaymentType")
	private String paymentType;

	@JsonProperty("PaymentTypeId")
	private String paymentTypeId;
		
	@JsonProperty("PaymentRefNo")
	private String paymentRefNo;
	
	@JsonProperty("BankName")
	private String bankName;
		
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("PaymentDate")
	private Date paymentDate;
	
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
