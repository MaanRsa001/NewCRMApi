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
@IdClass(ClaimOtherDetailsId.class)
@Table(name="CLAIM_OTHER_DETAILS")
public class ClaimOtherDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
		@Column(name="CLAIMOTHERDETAILSID",nullable=false)  
		private Integer    claimOtherDetailsId ; 

	    @Id
	    @Column(name="CLAIMID",nullable=false) 
		private Integer    claimid;
	    //--- ENTITY DATA FIELDS 	    
	   	    
	
	    @Temporal(TemporalType.DATE)
		@Column(name="CLAIMSETTLEMENTDATE", nullable = false)     
		private Date  claimSettlementDate;
		
	   
		@Column(name="DOCUMENTSREQUIREDYN", length=2)  
		private String    documentsRequiredYN;
		
	    
		@Column(name="DOCUMENTSSUBMITTEDYN", length=2)  
		private String    documentsSubmittedYN;
		 
		@Column(name="ONACCOUNTAMOUNT")  
		private Double    onAccountAmount;
		
		@Column(name="SALVAGEAMOUNT")  
		private Double    salvageAmount;
		 
	  
		@Column(name="NETCLAIMASSESSED")  
		private Double    NetClaimAssessed;
		
		@Column(name="CLAIMFILETYPE", length=100)  
		private String    claimFileType;
		 
		@Column(name="CLAIMFILETYPEID")  
		private Integer    claimFileTypeId;
	
		@Column(name="REFERENCENUMBER", length=100)  
		private String    referenceNumber;
		 
		
		@Column(name="CLAIMPROCESSINGSTATUS", length=100)  
		private String    claimProcessingStatus;
		
		@Column(name="CLAIMPROCESSINGSTATUSID")  
		private Integer    claimProcessingStatusId;
	 
		 
		@Column(name="CLAIMSTATUS", length=100)  
		private String    claimStatus;
		
		@Column(name="CLAIMSTATUSID")  
		private Integer    claimStatusId;
	  
		@Column(name="CLAIMPAYMENTDETAILS", length=100)  
		private String    claimPaymentDetails;
		
		@Column(name="REMARKS", length=200)  
		private String    remarks;
		  
		 
		
		
		 
		  
		
		
		
		 	 
	
  
   
	   

	    
}
