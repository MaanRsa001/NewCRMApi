package com.maan.crm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="STATUS_MASTER")
@IdClass(StatusMasterId.class)
public class StatusMaster {

	@Id
	@Column(name="STATUS_CODE",length=100,nullable=false)
	private String statusCode;
	
	@Id
	@Column(name="SUB_STATUS_CODE",length=100,nullable=false)
	private String subStatusCode;
	
	@Column(name="STATUS_NAME",length=100)
	private String statusName;
	

	@Column(name="SUB_STATUS_NAME",length=100)
	private String subStatusName;
	

	@Column(name="STATUS",length=1)
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	@Column(name="ORDER_ID")
	private Integer orderId;
	

	
}
