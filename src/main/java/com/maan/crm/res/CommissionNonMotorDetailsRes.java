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
public class CommissionNonMotorDetailsRes {

	@JsonProperty("Commissionid")
	private int commissionId;
	

	@JsonProperty("Amendid")
	private int amendId;

	@JsonProperty("Policyid")
	private int policyId;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("Commprocesseddate")
	private Date commProcessedDate;
	

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("Commgenerationdate")
	private Date commGenerationDate;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("Entrydate")
	private Date entryDate;
	
	@JsonProperty("Commissionbasepremium")
	private Double commissionBasePremium;
	
	
	@JsonProperty("Commissionamountper")
	private Double commissionAmountPer;
	
	@JsonProperty("Commissionamount")
	private Double commissionAmount;

	

	@JsonProperty("Flatcommissionamount")
	private Double flatCommissionAmount;

	@JsonProperty("Totalcommissionamount")
	private Double totalCommissionAmount;
	
	@JsonProperty("Commissiongst")
	private Double commissionGST; 
	
	
	
	@JsonProperty("Totalcommissionwithgst")
	private Double totalCommissionWithGST;
	
	
	@JsonProperty("Payoutbasepremium")
	private Double payoutBasePremium;
	
	@JsonProperty("Payoutamountper")
	private Double payoutAmountPer;
	

	@JsonProperty("Payoutamount")
	private Double payoutAmount;

	@JsonProperty("Flatpayoutamount")
	private Double flatPayoutAmount;
	
	@JsonProperty("Totalpayoutamount")
	private Double totalPayoutAmount; 
	  
	 
	@JsonProperty("Payoutgst")
	private Double payoutGST;
	
	@JsonProperty("Totalpayoutwithgst")
	private Double totalPayoutWithGST;	                      
}
