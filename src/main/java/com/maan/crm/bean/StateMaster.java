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
@IdClass(StateMasterId.class)
@Table( name ="state_master")
public class StateMaster implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//--- ENTITY PRIMARY KEY 
  
	@Id
    @Column(name="STATE_ID",nullable=false)
    private Integer stateId;  
	
    @Id
    @Column(name="COUNTRY_ID", length=100)
    private Integer    countryId ;
    
    @Id
    @Column(name="INSCOMPANYID", length=100, nullable=false)
    private String insCompanyId;
    
	@Id
	@Temporal(TemporalType.DATE)
    @Column(name="EFFECTIVE_DATE",nullable=false,length=100)
    private Date effectiveDate;
  
    @Id
    @Column(name= "AMEND_ID", nullable=false)
    private Integer amendId ;
    
   
    //--- ENTITY DATA FIELDS 
    @Column(name="STATE_NAME", length=100)
    private String    stateName ;

    @Column(name="STATUS", length=100)
    private String    status ;
    
    @Temporal(TemporalType.DATE)
    @Column(name="ENTRY_DATE")
    private Date      entryDate ;
    
    @Column(name="STATE_TYPE", length=100)
    private String     stateType;
    
    @Column(name="XGEN_CODE", length=500)
    private String    xgenCode;
    
    @Column(name="GST_YN", length=100)
    private String    gstYn;
    
    @Column(name="GST_CODE", length=500)
    private String gstCode;
    
    @Column(name="REMARKS")
    private String remarks;

}