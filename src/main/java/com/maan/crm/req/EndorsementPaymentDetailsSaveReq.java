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
public class EndorsementPaymentDetailsSaveReq {


	
	@JsonProperty("EndorsementId")
	private int endorsementId;
	

	@JsonProperty("EndorsementPaymentId")
	private int endorsementPaymentId;

	
	@JsonProperty("PaymentType")
	private String paymentType;
	
	@JsonProperty("PaymentTypeId")
	private int paymentTypeId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("PaymentDate")
	private Date paymentDate;
	
	@JsonProperty("PaymentRefNo")
	private String paymentRefNo;
	
	
	@JsonProperty("BankName")
	private String bankName;
	
	@JsonProperty("PaymentAmount")
	private Double paymentAmount;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("PaymentCollectedDate")
	private Date paymentCollectedDate;

	@JsonProperty("Remarks")
	private String remarks;

	
	
	 
	 
	 
	 
	 
	
	 
	  
	 
	
	                                 
}
