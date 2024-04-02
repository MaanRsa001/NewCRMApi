package com.maan.crm.res;

import java.io.Serializable;
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

public class ClaimDetailsRes implements Serializable  {

	private static final long serialVersionUID = 1L;

    
	@JsonProperty("Claimid")
	private Integer claimid;
	
	@JsonProperty("PolicyId")
    private Integer policyId;

	@JsonProperty("ClaimReferenceNo")
    private String claimReferenceNo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("ClaimReceiptDate")
    private Date claimReceiptDate;  

	@JsonProperty("AssetIdentificationNo")
    private String assetIdentificationNo;

	@JsonProperty("EstimatedLoss")
    private Double estimatedLoss;

	@JsonProperty("LossLocation")
    private String lossLocation;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("DateOfLoss")
	private Date dateOfLoss;  

	@JsonProperty("BriefDescriptionOfLoss")
	private Double briefDescriptionOfLoss;  

	@JsonProperty("SurveyorName")
	private String surveyorName;  

	@JsonProperty("SurveyorContactPerson")
	private String surveyorContactPerson;  

	@JsonProperty("SurveyorContactDetails")
	private String surveyorContactDetails;  

	@JsonProperty("SiteContactDetails")
 	private String siteContactDetails;  

    
}