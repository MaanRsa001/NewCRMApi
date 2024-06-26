/* 
*  Copyright (c) 2019. All right reserved
 * Created on 2022-05-14 ( Date ISO 2022-05-14 - Time 15:00:24 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

/*
 * Created on 2022-05-14 ( 15:00:24 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Domain class for entity "ClaimLoginUserDetails"
 *
 * @author Telosys Tools Generator
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
@Builder
@IdClass(ProductMasterId.class)
@Table(name = "PRODUCT_MASTER")

public class ProductMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	// --- ENTITY PRIMARY KEY
	@Id
	@Column(name = "PRODUCT_ID", nullable = false)
	private Integer productId;

	@Id
	@Column(name = "INS_COMPANY_ID", length=100,nullable = false)
	private String insCompanyId;
	
	@Id
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "EFFECTIVE_DATE", nullable = false)
	private Date effectiveDate;
	
	@Id
	@Column(name = "AMEND_ID", nullable = false)
	private Integer amendId;
	//---ENTITY

	@Column(name = "PRODUCT_NAME",length = 100)
	private String productName;
	
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "QUANTITY")
	private Integer quantity;

	@Column(name = "CATEGORY",length = 100)
	private String category;
	
	@Column(name = "DESCRIPTION",length = 100)
	private String description;

	@Column(name = "MANUFACTURE_YEAR",length = 100)
	private String manufactureYear;
	
	@Column(name = "CLASS_TYPE_ID")
	private Integer classTypeId;

	@Column(name = "CLASS_TYPE",length=100)
	private String classType;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "ENTRY_DATE")
	private Date entryDate;

	@Column(name = "Status",length=100)
	private String status;

}
