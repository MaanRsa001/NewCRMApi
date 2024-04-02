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
@Table(name="SUB_STATUS_MASTER")
@IdClass(SubStatusMasterId.class)
public class SubStatusMaster {

	@Id
	@Column(name="STATUS_CODE",length=100,nullable=false)
	private String statusCode;
	
	@Id
	@Column(name="SUB_STATUS_CODE",length=100,nullable=false)
	private String subStatusCode;
	
	@Id
	@Column(name="USER_TYPE",length=100,nullable=false)
	private String userType;
	
	@Column(name="NEXT_STATUS",length=100,nullable=false)
	private String nextStatus;
	
	@Column(name="STATUS",length=1)
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;
	
	
}
