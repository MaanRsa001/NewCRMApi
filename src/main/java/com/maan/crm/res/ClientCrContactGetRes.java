package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientCrContactGetRes {

	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("ContactId")
	private String clientcorContactId;

	@JsonProperty("ContactPersonName")
	private String contactPersonName;

	@JsonProperty("ContactPersonDesigination")
	private String contactPersonDesigination;

	@JsonProperty("MobileNo")
	private String mobileNo;

	@Column(name = "Email")
	private String email;
	
	@JsonProperty("Status")
	private String status;

	@JsonFormat(pattern="dd/MM/YYYY")
	@Column(name = "EntryDate")
	private Date entryDate;


}
