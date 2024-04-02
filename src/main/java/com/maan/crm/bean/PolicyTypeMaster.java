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
@IdClass(PolicyTypeMasterId.class)
@Table( name ="policy_type_master")
public class PolicyTypeMaster implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//--- ENTITY PRIMARY KEY 
  
	@Id
    @Column(name="POLICYTYPE_ID",nullable=false)
    private Integer policytypeId;  
    
	@Id
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="EFFECTIVE_DATE",nullable=false,length=100)
    private Date effectiveDate;
  
    @Id
    @Column(name= "AMEND_ID", nullable=false)
    private Integer amendId ;
    
    @Id
    @Column(name="CLASS_ID", length=100)
    private Integer    classId ;
   
    @Id
    @Column(name="INS_COMPANY_ID", length=100)
    private String  insCompanyId;
    
    //--- ENTITY DATA FIELDS 
    @Column(name="POLICYTYPE_NAME", length=100)
    private String    policytypeName ;

    @Column(name="STATUS", length=100)
    private String    status ;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="ENTRY_DATE")
    private Date      entryDate ;
 
    @Column(name="REMARKS")
    private String remarks;

}