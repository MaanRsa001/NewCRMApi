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
@IdClass(EnquiryDetailsId.class)
@Table(name="enquiry_details")
public class EnquiryDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	 
	    //--- ENTITY PRIMARY KEY 
		@Id
	    @Column(name="ENQUIRY_ID", nullable=false, length=100)
	    private String enquiryId;
		
	    @Id
	    @Column(name="LEAD_ID", nullable=false, length=100)
	    private String leadId;

	    @Id
	    @Column(name="CLIENT_REF_NO", nullable=false, length=100)
	    private String clientRefNo;
	    
	     @Column(name="ENQUIRY_DESCRIPTION") 
	     private String     enquiryDescription ;
	     
		 @Column(name="BUSINESS_TYPE")
		 private String businessType;
		 
		 @Column(name="BUSINESS_TYPE_ID") 
		 private Integer businessTypeId ;
		 
		 @Column(name="CLASS_ID")
		 private Integer     classId ;
		 
		 @Column(name="CLASS_DESC")
		 private String     classDesc ;
		 
		 @Column(name="POLICYTYPE_ID")
		 private Integer     policytypeId ;
		 
		 @Column(name="POLICYTYPE")
		 private String     policytype ;
		 
		 @Column(name="SUM_INSURED")
		 private Double     sumInsured ;
		 
		 @Column(name="SUGGEST_PREMIUM")
		 private Double     suggestPremium ;
		 
		 @Column(name="ENQ_PREMIUM_RATE")
		 private Double enqPremiumRate;
		 
		 @Column(name="ALLOTTED_UW")
		 private String     allotedUw ;
		 
		 @Column(name="ALLOTTED_UW_ID")
		 private String     allotedUwId ;
		 
		 @Column(name="CREATED_BY")
		 private String     createdBy ;
		 
		 @Column(name="CREATED_USERTYPE")
		 private String     createdUsertype ;
		 
		 @Temporal(TemporalType.TIMESTAMP)
		 @Column(name="UPDATED_DATE")
		 private Date updatedDate ;
		 
		 @Column(name="UPDATED_BY")
		 private String     updatedBy ;
		 
		 @Column(name="UPDATER_USERTYPE")
		 private String     updaterUsertype ;
		 
		 @Column(name="LAST_REMARKS")
		 private String     lastRemarks ;
		 
		 @Column(name="ENQ_STATUS")
		 private String     enqStatus ;
		 
		 @Column(name="ENQ_STATUS_DESC")
		 private String     enqStatusDesc ;
		 
		 @Temporal(TemporalType.DATE)
		 @Column(name="ENTRY_DATE")
		 private Date entryDate ;
		 
		 @Column(name="INS_COMPANY_ID")
		 private String     insCompanyId ;
		 
		 @Column(name="BRANCH_CODE",length=100)
		 private String     branchCode ;
		 
		 @Column(name="BROUGHT_CODE",length=100)
		 private String     broughtCode ;
		 
		 @Column(name="BROUGHT_NAME",length=100)
		 private String     broughtName ;
		 
		 @Column(name="BROUGHT_BY",length=100)
		 private String     broughtBy ;
		 
		 
}
