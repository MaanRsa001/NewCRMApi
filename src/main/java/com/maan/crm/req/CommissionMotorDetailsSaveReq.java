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
public class CommissionMotorDetailsSaveReq {

	@JsonProperty("Commissionid")
	private int commissionId;

	@JsonProperty("Amendid")
	private int amendId;

	@JsonProperty("Policyid")
	private int policyId;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("Commprocesseddate")
	private Date commProcessedDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("Commgenerationdate")
	private Date commGenerationDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
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
