package com.maan.crm.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MOTOR_PLATECHAR_MASTER")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PlateCharMasterId.class)
@Data
public class PlateCharMaster {

	@Id
	@Column(name = "PLATE_DEFINED_BY")
	private Double plateDefinedBy;
	
	@Id
	@Column(name = "PLATE_CHAR_ID")
	private Double plateCharId;
	
	@Id
	@Column(name = "AMEND_ID")
	private Double amendId;
	
	@Column(name = "PLATE_CHAR_EN")
	private String plateCharEn;
	
	@Column(name = "PLATE_CHARS_EN")
	private String plateCharsEn;
	
	@Column(name = "PLATE_CHAR1_EN")
	private String plateChar1En;
	
	@Column(name = "PLATE_CHAR2_EN")
	private String plateChar2En;
	
	@Column(name = "PLATE_CHAR3_EN")
	private String plateChar3En;
	
	@Column(name = "PLATETYPE_CODE")
	private Double plateTypeCode;
	
	@Column(name = "PLATETYPE_DESC_EN")
	private String plateTypeDescEn;
	
	@Column(name = "PLATETYPE_DESC_AR")
	private String plateTypeDescAr;
	
	@Column(name = "PLATE_CHAR_AR")
	private String plateCharAr;
	
	@Column(name = "PLATE_CHARS_AR")
	private String plateCharsAr;
	
	@Column(name = "PLATE_CHAR1_AR")
	private String plateChar1Ar;
	
	@Column(name = "PLATE_CHAR2_AR")
	private String plateChar2Ar;
	
	@Column(name = "PLATE_CHAR3_AR")
	private String plateChar3Ar;
	
	@Column(name = "ENTRY_DATE")
	private Date entryDate;
	
	@Column(name = "ENTRY_MODE")
	private String entryMode;
	
	@Column(name = "UPLOADED_BY")
	private String uploadedBy;
	
	@Column(name = "STATUS", length = 1)
	private String status;
	
	
}
