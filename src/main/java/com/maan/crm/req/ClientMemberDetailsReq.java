package com.maan.crm.req;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientMemberDetailsReq {

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

	@JsonFormat(pattern = "dd/mm/yyyy")
	@JsonProperty("DateOfBirth")
	private Date dateOfBirth;

}
