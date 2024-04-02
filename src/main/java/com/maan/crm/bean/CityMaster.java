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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(CityMasterId.class)
@Table(name="city_master")
public class CityMaster implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	 
	    //--- ENTITY PRIMARY KEY 
	    @Id
	    @Column(name="CITY_ID", nullable=false)
	    private Integer     cityId ;

	    @Id
	    @Temporal(TemporalType.DATE)
	    @Column(name="EFFECTIVE_DATE", nullable=false)
	    private Date       effectiveDate ;

	    @Id
	    @Column(name="AMEND_ID", nullable=false)
	    private Integer     amendId ;

	    @Id
	    @Column(name="STATE_CODE", nullable=false)
	    private Integer     stateCode ;


	    @Id
	    @Column(name= "INS_COMPANY_ID",length=100, nullable=false)
	    private String insCompanyId;
	    
	    //--- ENTITY DATA FIELDS 
	    @Column(name="CITY_NAME", length=50)
	    private String     cityName ;

	    @Column(name="STATUS", length = 5)
	    private String status ;

	    @Column(name="REMARKS", length = 100)
	    private String remarks ;


	    @Temporal(TemporalType.DATE)
	    @Column(name="ENTRY_DATE")
	    private Date       entryDate ;



}


	    


