package com.maan.crm.res;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.bean.ClientDetails;

import lombok.Data;

@Data
public class ClientCountRes {

	@JsonProperty("ClientCount")
	private Long clientCount;
	
	@JsonProperty("ClientDetails")
	private List<ClientDetails> clientDetails;


	}
