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

public class TicketAdminSaveReq {

	@JsonProperty("TicketId")
	private String ticketId;

	@JsonProperty("IssuerId")
	private Integer issuerId;


	@JsonProperty("IssuerName")
	private String issuerName;
	
	@JsonProperty("StatusId")
	private Integer statusId;

	@JsonProperty("Status")
	private String status;

}
