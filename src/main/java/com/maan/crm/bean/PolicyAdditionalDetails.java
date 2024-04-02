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
@IdClass(PolicyAdditionalDetailsId.class)
@Table(name="policy_additional_details")
public class PolicyAdditionalDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
		@Column(name="ADDITIONALDETAILSID",nullable=false)  
		private Integer    additionalDetailsId ; 

	    @Id
	    @Column(name="POLICYID",nullable=false) 
		private Integer    policyId;

	    	    
	    //--- ENTITY DATA FIELDS 	    
	   	 
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="ISSUEDATE", nullable = false)
	    private Date issueDate;
	    
		@Column(name="SOURCE", length= 200)  
		private String source;
		
		@Column(name="SOURCEID",nullable=false) 
		private Integer    sourceId;

		
		@Column(name="POS", length= 200)  
		private String pOS;
		
		@Column(name="POSID",nullable=false) 
		private Integer    pOSId;

		@Column(name="USER", length= 200)  
		private String user;
		
		@Column(name="USERID",nullable=false) 
		private Integer    userId;
		
		@Column(name="LOCATION", length= 200)  
		private String location;
		
		@Column(name="PREVIOUSPOLICYNO", length= 200)  
		private String previousPolicyNo;
		
		@Column(name="PREVIOUSINSURER", length= 200)  
		private String previousInsurer;
		
		@Column(name="PREVIOUSINSURERID",nullable=false) 
		private Integer    previousInsurerId;
		
		@Column(name="PREVIOUSINSURANCEPLAN", length= 200)  
		private String previousInsurancePlan;
		
		@Column(name="PREVIOUSSOURCE", length= 200)  
		private String previousSource;
		
		@Column(name="PREVIOUSSOURCEID",nullable=false) 
		private Integer    previousSourceId;
		
	    
		@Column(name="PREVIOUSPOS", length =200)  
		private String previousPOS;
	    
		@Column(name="PREVIOUSPOSID",nullable=false) 
		private Integer    previousPOSId;
		
		@Column(name="COMMISSIONGENERATION", length =200)  
		private String commissionGeneration;
	    
		@Column(name="COMMISSIONGENERATIONID",nullable=false) 
		private Integer    commissionGenerationId;
		
		@Column(name="DEDUCTIBLEDETAILS", length =300)  
		private String deductibleDetails;
	    
		@Column(name="POLICYADDITIONALINFORMATION", length =300)  
		private String policyAdditionalInformation;
	    
		@Column(name="FILETYPE", length =200)  
		private String fileType;
	    
		@Column(name="FILETYPEID",nullable=false) 
		private Integer    fileTypeId;
		
		@Column(name="REFERENCENUMBER", length =100)  
		private String referenceNumber;
	    
		@Column(name="OTHERREFERENCENUMBER", length =100)  
		private String otherReferenceNumber;
	    
		@Column(name="POLICYRECEIVEDYN", length =2)  
		private String policyReceivedYN;
	    
		@Column(name="POLICYVERIFIEDYN", length =2)  
		private String policyVerifiedYN;
	    
		@Column(name="POLICYVERIFIEDBY", length =100)  
		private String policyVerifiedBy;
	    
		@Column(name="POLICYCANCELLEDYN", length =2)  
		private String policyCancelledYn;
	    
		@Column(name="POLICYCANCELLEDYNBY", length =200)  
		private String policyCancelledYnBy;
	    
		@Column(name="POLICYSTATUS", length =100)  
		private String policyStatus;
	    
		@Column(name="POLICYSTATUSID",nullable=false) 
		private Integer    policyStatusId;
		
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="ENTRYDATE", nullable = false)
		private Date entryDate;
}
