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
public class ClaimOtherDetailsSaveReq {

	@JsonProperty("ClaimOtherDetailsId")
	private int claimOtherDetailsId;

	@JsonProperty("claimid")
	private int claimid;


	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("ClaimSettlementDate")
	private Date claimSettlementDate;

	@JsonProperty("DocumentsRequiredYN")
	private String documentsRequiredYN;

	@JsonProperty("DocumentsSubmittedYN")
	private String documentsSubmittedYN;

	@JsonProperty("OnAccountAmount")
	private Double onAccountAmount;

	@JsonProperty("SalvageAmount")
	private Double salvageAmount;

	@JsonProperty("NetClaimAssessed")
	private Double netClaimAssessed;

	@JsonProperty("ClaimFileType")
	private String claimFileType;

	@JsonProperty("ClaimFileTypeId")
	private int claimFileTypeId;

	@JsonProperty("ReferenceNumber")
	private String referenceNumber;

	@JsonProperty("ClaimProcessingStatus")
	private String claimProcessingStatus;

	@JsonProperty("ClaimProcessingStatusId")
	private int claimProcessingStatusId;

    @JsonProperty("ClaimStatus")
	private String claimStatus;

	@JsonProperty("ClaimStatusId")
	private int claimStatusId;

 
    @JsonProperty("ClaimPaymentDetails")
	private String claimPaymentDetails;

	@JsonProperty("Remarks")
	private String remarks;

 
   
}
