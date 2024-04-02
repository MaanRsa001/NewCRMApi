package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class InsuranceCompanyMasterRes {

	@JsonProperty("INS_ID")
	private String insId;

	@JsonProperty("INS_NAME")
	private String insName;

	@JsonProperty("INS_ADDRESS")
	private String insAddress;

	@JsonProperty("INS_COUNTRY")
	private Integer insCountry;

	@JsonProperty("INS_CITY")
	private Integer insCity;

	@JsonProperty("INS_ZIPCODE")
	private String insZipcode;

	@JsonProperty("INS_PHONE")
	private String insPhone;

	@JsonProperty("MOBILE_NO")
	private String mobileNo;

	@JsonProperty("INS_EMAIL")
	private String insEmail;

	@JsonProperty("STATUS")
	private String status;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("ENTRY_DATE")
	private Date entryDate;

	@JsonProperty("REMARKS")
	private String remarks;

}
