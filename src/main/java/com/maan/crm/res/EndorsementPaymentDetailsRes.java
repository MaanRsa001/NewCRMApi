package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class EndorsementPaymentDetailsRes {


	
	@JsonProperty("EndorsementId")
	private int endorsementId;
	

	@JsonProperty("EndorsementPaymentId")
	private int endorsementPaymentId;

	
	@JsonProperty("PaymentType")
	private String paymentType;
	
	@JsonProperty("PaymentTypeId")
	private int paymentTypeId;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("PaymentDate")
	private Date paymentDate;
	
	@JsonProperty("PaymentRefNo")
	private String paymentRefNo;
	
	
	@JsonProperty("BankName")
	private String bankName;
	
	@JsonProperty("PaymentAmount")
	private Double paymentAmount;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("PaymentCollectedDate")
	private Date paymentCollectedDate;

	@JsonProperty("Remarks")
	private String remarks;

	
	
	 
	 
	 
	 
	 
	
	 
	  
	 
	
	                                 
}
