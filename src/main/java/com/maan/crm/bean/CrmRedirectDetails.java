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
@IdClass(CrmRedirectDetailsId.class)
@Table(name="crm_redirect_details")
public class CrmRedirectDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	 
	    //--- ENTITY PRIMARY KEY 
	    @Id
	    @Column(name="SCREEN_ID", nullable=false)
	    private Integer     screenId ;

	    @Id
	    @Column(name="INS_COMPANY_ID", nullable=false)
	    private String insCompanyId ;

	  
	    
	    //--- ENTITY DATA FIELDS 
	    @Column(name="SCREEN_NAME", length=50)
	    private String     screenName ;

	    @Column(name="STATUS", length = 5)
	    private String status ;

	    @Column(name="REDIRECT_URL_LINK", length = 100)
	    private String redirectUrlLink ;


	    @Temporal(TemporalType.DATE)
	    @Column(name="ENTRY_DATE")
	    private Date       entryDate ;



}


	    


