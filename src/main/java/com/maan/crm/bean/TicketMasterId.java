package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class TicketMasterId  implements Serializable{

	public static final long SerialVersionUID = 1L;
	
	private String statusCode;
	private String statusDescription;
	private String statusType;

	
}
