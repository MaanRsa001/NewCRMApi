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
@IdClass(SourceMasterId.class)
@Table(name="source_master")
public class SourceMaster implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	 
	    //--- ENTITY PRIMARY KEY 
	    @Id
	    @Column(name="SOURCE_ID", nullable=false)
	    private Integer     sourceId ;

	    @Id
	    @Temporal(TemporalType.DATE)
	    @Column(name="EFFECTIVE_DATE", nullable=false)
	    private Date       effectiveDate ;

	    @Id
	    @Column(name="AMEND_ID", nullable=false)
	    private Integer     amendId ;
	    
	    @Id
	    @Column(name="Ins_Company_Id", nullable=false)
	    private String insCompanyId ;

	    //--- ENTITY DATA FIELDS 
	    @Column(name="SOURCE_NAME", length=50)
	    private String     sourceName ;

	    @Column(name="STATUS", length = 5)
	    private String status ;

	    @Column(name="REMARKS", length = 100)
	    private String remarks ;

	    @Column(name="SEARCH_YN", length = 1)
	    private String searchyn ;

	    @Temporal(TemporalType.DATE)
	    @Column(name="ENTRY_DATE")
	    private Date       entryDate ;

	    @Column(name="REFERENCE_DETAILS_YN", length = 1)
	    private String referenceDetailsYn ;
				}


	    


