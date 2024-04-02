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
public class CommissionMotorDetailsRes {

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

	@JsonProperty("Insowndamageaddonper")
	private Double insOwnDamageAddOnPer;

	@JsonProperty("Insowndamageaddonamount")
	private Double insOwnDamageAddOnAmount;

	@JsonProperty("Insthirdpartyper")
	private Double insThirdPartyPer;

	@JsonProperty("Insthirdpartyamount")
	private Double insThirdPartyAmount;

	@JsonProperty("Insnetper")
	private Double insNetPer;

	@JsonProperty("Insnetamount")
	private Double insNetAmount;

	@JsonProperty("Insflat")
	private Double insFlat;

	@JsonProperty("Instotal")
	private Double insTotal;

	@JsonProperty("Insgstper")
	private Double insGstPer;

	@JsonProperty("Insgstamount")
	private Double insGstAmount;

	@JsonProperty("Instotalwithgst")
	private Double insTotalWithGST;

	@JsonProperty("Payoutowndamageaddonper")
	private Double payoutOwnDamageAddOnPer;

	@JsonProperty("Payoutowndamageaddonamount")
	private Double payoutOwnDamageAddOnAmount;

	@JsonProperty("Payoutthirdpartyper")
	private Double payoutThirdPartyPer;

	@JsonProperty("Payoutthirdpartyamount")
	private Double payoutThirdPartyAmount;

	@JsonProperty("Payoutnetper")
	private Double payoutNetPer;

	@JsonProperty("Payoutnetamount")
	private Double payoutNetAmount;

	@JsonProperty("Payoutflat")
	private Double payoutFlat;

	@JsonProperty("Payouttotal")
	private Double payoutTotal;

	@JsonProperty("Payoutgstper")
	private Double payoutGstPer;

	@JsonProperty("Payoutgstamount")
	private Double payoutGstAmount;

	@JsonProperty("Payouttotalwithgst")
	private Double payoutTotalWithGST;

}
