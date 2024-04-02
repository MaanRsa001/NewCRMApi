package com.maan.crm.res;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientMemberDetailsGetRes {

	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("ClientMemberId")
	private String clientMemberId;

	@JsonProperty("MemberName")
	private String memberName;

	@JsonProperty("Relation")
	private String relation;

	@JsonProperty("Gender")
	private String gender;

	@JsonProperty("GenderId")
	private String genderId;

	@JsonProperty("Age")
	private String age;

	@JsonFormat(pattern="dd/MM/YYYY")
	@JsonProperty("DateOfBirth")
	private Date dateOfBirth;
	
	@JsonProperty("Status")
	private String status;

	@JsonFormat(pattern="dd/MM/YYYY")
	@Column(name = "EntryDate")
	private Date entryDate;


}
