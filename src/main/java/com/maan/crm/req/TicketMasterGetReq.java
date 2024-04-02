package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TicketMasterGetReq {

	@JsonProperty("TicketId")
	private String ticketId;
}
