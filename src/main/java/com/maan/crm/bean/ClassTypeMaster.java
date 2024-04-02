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
@IdClass(ClassTypeMasterId.class)
@Table( name ="CLASS_TYPE_MASTER")
public class ClassTypeMaster implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//--- ENTITY PRIMARY KEY 
  
	@Id
    @Column(name="CLASS_ID",nullable=false)
    private Integer classId;  
    
	@Id
	@Temporal(TemporalType.DATE)
    @Column(name="EFFECTIVE_DATE",nullable=false,length=100)
    private Date effectiveDate;
  
    @Id
    @Column(name= "AMEND_ID", nullable=false)
    private Integer amendId ;
    
    @Id
    @Column(name= "INS_COMPANY_ID",length=100, nullable=false)
    private String insCompanyId;
   
    //--- ENTITY DATA FIELDS 
    @Column(name="CLASS_NAME", length=100)
    private String    className ;

    @Column(name="STATUS", length=100)
    private String    status ;
    
    @Temporal(TemporalType.DATE)
    @Column(name="ENTRY_DATE")
    private Date      entryDate ;
 
    @Column(name="REMARKS")
    private String remarks;

}