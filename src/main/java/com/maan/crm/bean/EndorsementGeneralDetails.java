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
@IdClass(EndorsementGeneralDetailsId.class)
@Table(name="endorsement_general_details")
public class EndorsementGeneralDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
		@Column(name="ENDORSEMENTID",nullable=false)  
		private Integer    endorsementId ; 

	    @Id
	    @Column(name="POLICYID",nullable=false) 
		private Integer    policyId;

	    	    
	    //--- ENTITY DATA FIELDS 	    
	   	    
		@Column(name="ENDORSEMENTTYPE", length= 100)  
		private String endorsementType;
	    
		
		@Column(name="ENDORSEMENTTYPEID")  
		private Integer    endorsementTypeId;
		
		

		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="RECEIVEDDATE", nullable = false)     
		private Date  receivedDate;
		
	    @Column(name="ENDORSEMENTSTATUS", length= 100)  
		private String endorsementStatus;
	    
		
		@Column(name="ENDORSEMENTSTATUSID")  
		private Integer    endorsementStatusId;
		
	    
		@Column(name="ENDORSEMENTNUMBER", length= 100)  
		private String endorsementNumber;
	    
	    
		@Column(name="ENDORSEMENTREASON1", length =200)  
		private String endorsementReason1;
	    
		@Column(name="ENDORSEMENTREASON1ID")  
		private Integer    endorsementReason1Id;
		

		@Column(name="ENDORSEMENTREASON2", length =200)  
		private String endorsementReason2;
	    
		@Column(name="ENDORSEMENTREASON2ID")  
		private Integer    endorsementReason2Id;
		
	     
		@Column(name="ENDORSEMENTREASON3", length =200)  
		private String endorsementReason3;
	    
		@Column(name="ENDORSEMENTREASON3ID")  
		private Integer    endorsementReason3Id;
		 
	      
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
			@Column(name="EFFECTIVEDATE", nullable = false)     
			private Date  effectiveDate;
			 
	     
		 @Column(name="ENDORSEMENTDESCRIPTION", length =300)  
			private String endorsementDescription;
	      
		 @Column(name="ENDORSEMENTCOMMENT", length =300)  
			private String endorsementComment;
   
   
  
  
  
  
   
  
   
	   

	    
}
