package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CampaignDetailsId.class)
@Table(name="campaign_details")
public class CampaignDetails implements Serializable {

	@Id
	@Column(name="CAMPAIGN_ID",length=100, nullable=false)
	private String campaignId;
	
	@Id
	@Column(name="CUSTOMER_ID",length=100, nullable=false)
	private String customerId;
	
	@Column(name="CLIENT_NAME", length =100)
	private String clientName;
	
	
	@Column(name="MOBILE_NUMBER",length=10)
	private String mobileNumber;
	

	@Column(name="EMAIL_ID", length=100)
	private String emailId;
	
	
	@Column(name="OCCUPATION", length=100)
	private String occupation;
	
	
	@Column(name="CRNO", length=100)
	private String crno;
	
	
	@Column(name="CLIENT_REF_NO", length=100)
	private String clientRefNo;
	
	
	@Column(name="LEAD_ID")
	private String leadId;
	

	@Column(name="CLIENT_TYPE", length=100)
	private String clientType;
	

	@Column(name="CLIENT_TYPE_ID")
	private String clientTypeId;

	@Column(name="TITLE", length=10)
	private String title;
	

	@Column(name="TITLE_ID")
	private String titleId;
	
	
	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;

	
	@Column(name = "ANNUAL_INCOME", length = 100)
	private String annualIncome;

	@Column(name = "ANNUAL_INCOME_ID")
	private String annualIncomeId;

	
	@Column(name = "AGE")
	private String age;
	
	
	@Column(name = "ADDRESS1", length = 100)
	private String address1;


	@Column(name = "ADDRESS2", length = 100)
	private String address2;
	
	@Column(name = "GENDER", length = 100)
	private String gender;

	@Column(name = "GENDER_ID")
	private String genderId;
	

	@Column(name = "CUSTOMFIELDS1", length = 100)
	private String customFields1;
	

	@Column(name = "CUSTOMFIELDS2", length = 100)
	private String customFields2;
	

	@Column(name = "CUSTOMFIELDS3", length = 100)
	private String customFields3;
	

	@Column(name = "CUSTOMFIELDS4", length = 100)
	private String customFields4;
	

	@Column(name = "CUSTOMFIELDS5", length = 100)
	private String customFields5;
	

	@Column(name = "CUSTOMFIELDS6", length = 100)
	private String customFields6;

	@Column(name = "CUSTOMFIELDS7", length = 100)
	private String customFields7;
	

	@Column(name = "CUSTOMFIELDS8", length = 100)
	private String customFields8;
	

	@Column(name = "CUSTOMFIELDS9", length = 100)
	private String customFields9;
	

	@Column(name = "CUSTOMFIELDS10", length = 100)
	private String customFields10;
	

	@Column(name = "CUSTOMFIELDS11", length = 100)
	private String customFields11;
	

	@Column(name = "CUSTOMFIELDS12", length = 100)
	private String customFields12;
	

	@Column(name = "CUSTOMFIELDS13", length = 100)
	private String customFields13;
	

	@Column(name = "CUSTOMFIELDS14", length = 100)
	private String customFields14;
	

	@Column(name = "CUSTOMFIELDS15", length = 100)
	private String customFields15;
}
