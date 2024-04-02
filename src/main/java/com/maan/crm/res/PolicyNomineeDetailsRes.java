package com.maan.crm.res;

import java.util.Date;

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
public class PolicyNomineeDetailsRes {

	@JsonProperty("NomineeId")
	private int nomineeId;

	@JsonProperty("PolicyId ")
	private int policyId;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Relation")
	private String relation;

	@JsonProperty("RelationId ")
	private int relationId;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("DOB  ")
	private Date dob;

	@JsonProperty("Gender")
	private String gender;

	@JsonProperty("GenderId")
	private int genderId;

	@JsonProperty("Benefit")
	private double benefit;
}
