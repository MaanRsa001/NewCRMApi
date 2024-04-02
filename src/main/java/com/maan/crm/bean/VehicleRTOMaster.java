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
@IdClass(VehicleRTOMasterId.class)
@Table(name="VEHICLE_RTO_MASTER")
public class VehicleRTOMaster implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	 
	    //--- ENTITY PRIMARY KEY 
	    @Id
	    @Column(name="RTO_CODE",length =10, nullable=false)
	    private String     rtoCode ;

	    @Id
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="EFFECTIVE_DATE", nullable=false)
	    private Date       effectiveDate ;

	    @Id
	    @Column(name="AMEND_ID", nullable=false)
	    private Integer     amendId ;
	    
	    @Id
	    @Column(name="INSCOMPANYID",length=100, nullable=false)
	    private String insCompanyId ;

	    //--- ENTITY DATA FIELDS 
	    @Column(name="RTO_DESC", length=100)
	    private String     rtoDesc ;

	    @Column(name="STATUS", length = 10)
	    private String status ;

	    @Column(name="REMARKS", length = 300)
	    private String remarks ;

	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="ENTRY_DATE")
	    private Date       entryDate ;


}


	    
 
               

