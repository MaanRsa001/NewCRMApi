package com.maan.crm.res;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientSearchCountRes {

	
	@JsonProperty("ClientCount")
	private  Long clientCount;
	
	@JsonProperty("ClientDetails")
	private  List<ClientSearchRes> clientDetails;
}
