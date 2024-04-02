package com.maan.crm.req;

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

public class CampaignInviteReq {

	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("MobileNumber")
	private String mobileNumber;

	@JsonProperty("EmailId")
	private String emailId;
	

	@JsonProperty("DateOfBirth")
	private String dateOfBirth;
	

	@JsonProperty("AnnualIncome")
	private String annualIncome;
	

	@JsonProperty("Occupation")
	private String occupation;
	

	@JsonProperty("CampaignId")
	private String campaignId;

	@JsonProperty("Age")
	private String age;
	
	

	@JsonProperty("Address1")
	private String address1;
	

	@JsonProperty("Address2")
	private String address2;
	

	@JsonProperty("Gender")
	private String gender;
	

	@JsonProperty("CustomFields1")
	private String customFields1;
	

	@JsonProperty("CustomFields2")
	private String customFields2;
	
	

	@JsonProperty("CustomFields3")
	private String customFields3;
	

	@JsonProperty("CustomFields4")
	private String customFields4;
	
	

	@JsonProperty("CustomFields5")
	private String customFields5;
	

	@JsonProperty("CustomFields6")
	private String customFields6;

	@JsonProperty("CustomFields7")
	private String customFields7;

	@JsonProperty("CustomFields8")
	private String customFields8;

	@JsonProperty("CustomFields9")
	private String customFields9;
	

	@JsonProperty("CustomFields10")
	private String customFields10;
	
	@JsonProperty("CustomFields11")
	private String customFields11;
	

	@JsonProperty("CustomFields12")
	private String customFields12;
	

	@JsonProperty("CustomFields13")
	private String customFields13;
	

	@JsonProperty("CustomFields14")
	private String customFields14;
	

	@JsonProperty("CustomFields15")
	private String customFields15;
}
