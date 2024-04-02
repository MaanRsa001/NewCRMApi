package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(PolicyAssuredDetailsId.class)
@Table(name = "POLICY_ASSURED_DETAILS")
public class PolicyAssuredDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --- ENTITY PRIMARY KEY

	@Id
	@Column(name = "ASSURED_ID", nullable = false, length = 50)
	private Integer assuredId;

	@Id
	@Column(name = "POLICYID", nullable = false, length = 50)
	private Integer policyId;

	// --- ENTITY DATA FIELDS
	@Column(name = "NAME", length = 100)
	private String name;

	@Column(name = "RELATION_ID")
	private Integer relationId;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "GENDERID")
	private Integer genderId;

	@Column(name = "SUMASSURED")
	private Double sumassured;

	@Column(name = "RELATION", length = 100)
	private String relation;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "DOB")
	private Date dob;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name ="ENTRY_DATE")
	private Date entryDate;

	@Column(name = "STATUS", length = 10)
	private String status;

}
