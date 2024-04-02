package com.maan.crm.req;

import java.util.Date;
import java.util.List;

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
public class CampaignMasterSaveReq {

	@JsonProperty("CampaignId")
	private String campaignId;
	
	@JsonProperty("CampaignName")
	private String campaignName;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("PromoCode")
	private String promoCode;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("StartDate")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EndDate")
	private Date endDate;
	
	@JsonProperty("Product")
	private String product;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("BranchCode")
	private String branchCode;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EntryDate")
	private Date entryDate;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("InsCompanyId")
	private String insCompanyId;
	
	@JsonProperty("ClassType")
	private String classType;
	

	@JsonProperty("ClassTypeId")
	private String classTypeId;

	@JsonProperty("PolicyType")
	private String policyType;
	
	@JsonProperty("PolicyTypeId")
	private String policyTypeId;
	

	@JsonProperty("LandingPageLink")
	private String landingPageLink;
	
	
	@JsonProperty("BusinessType")
	private String businessType;
	
	@JsonProperty("BusinessTypeId")
	private String businessTypeId;
	
	@JsonProperty("Offer")
	private List<String> offer;
	
	@JsonProperty("CampTemplateFormDetails")
	private List<CampaignTemplateFormSaveReq> campTemplateFormDetails; 
	
}
