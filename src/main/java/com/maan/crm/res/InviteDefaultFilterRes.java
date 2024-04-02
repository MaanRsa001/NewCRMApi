package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InviteDefaultFilterRes {

	@JsonProperty("ClientName")
	private String clientName;


	@JsonProperty("MobileNumber")
	private String mobileNumber;
	

	@JsonProperty("MailId")
	private String mailId;
}
