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
public class InsuranceCompanyMasterSaveReq {

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

}
