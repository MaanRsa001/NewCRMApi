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
@IdClass(MotorBodyTypeMasterId.class)
@Table(name = "MOTOR_BODYTYPE_MASTER")
public class MotorBodyTypeMaster implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --- ENTITY PRIMARY KEY

	@Id
	@Column(name = "BODY_ID", nullable = false)
	private Integer bodyId;

	@Id
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "EFFECTIVE_DATE", nullable = false, length = 100)
	private Date effectiveDate;

	@Id
	@Column(name = "AMEND_ID", nullable = false)
	private Integer amendId;

	@Id
	@Column(name = "INSCOMPANYID",length=100, nullable = false)
	private String insCompanyId;
	
	// --- ENTITY DATA FIELDS
	@Column(name = "BODY_NAME_EN", length = 100)
	private String bodyNameEn;

	@Column(name = "STATUS", length = 100)
	private String status;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "ENTRY_DATE")
	private Date entryDate;

	@Column(name = "REMARKS", length = 100)
	private String remarks;

	@Column(name = "SEATING_CAPACITY")
	private Integer seatingCapacity;
	
	@Column(name = "TONNAGE")
	private Integer tonnage;
	
	@Column(name = "CYCLINDERS")
	private Integer cyclinders;
	
	
}