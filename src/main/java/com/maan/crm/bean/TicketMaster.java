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
@IdClass(TicketMasterId.class)
@Table(name="TICKET_MASTER")
public class TicketMaster {

	public static final long serialVersionUID = 1L;
	
	@Id
	@Column(name ="STATUS_CODE",length=20, nullable =false)
	private String statusCode;
	
	@Id
	@Column(name="STATUS_TYPE", length=100)
	private String statusType;
	
	@Id
	@Column(name="STATUS_DESCRIPTION", length=100)
	private String statusDescription;
	
	
	@Column(name="STATUS", length=1)
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ENTRY_DATE")
	private Date entryDate;
}
