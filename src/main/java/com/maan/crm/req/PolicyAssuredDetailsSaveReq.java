package com.maan.crm.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyAssuredDetailsSaveReq {

	@JsonProperty("PolicyId")
	private Integer policyId;

	@JsonProperty("AssuredId")
	private Integer assuredId;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Relation")
	private String relation;

	@JsonProperty("RelationId")
	private Integer relationId;

	@JsonProperty("Gender")
	private String gender;

	@JsonProperty("GenderId")
	private Integer genderId;

	@JsonProperty("SumAssured")
	private Double sumassured;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("Dob")
	private Date dob;

}
