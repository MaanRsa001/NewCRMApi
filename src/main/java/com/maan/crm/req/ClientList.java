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

public class ClientList {

	@JsonProperty("ClientName")
	private String clientName;

	@JsonProperty("MobileNumber")
	private String mobileNumber;
	

	@JsonProperty("MailId")
	private String mailId;
}
