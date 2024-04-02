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
@IdClass(EndorsementFinancialDetailsId.class)
@Table(name="endorsment_financial_details")
public class EndorsementFinancialDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
		@Column(name="ENDORSEMENTID",nullable=false)  
		private Integer    endorsementId ; 

	    @Id
	    @Column(name="ENDORSMENTFINANCIALID",nullable=false) 
		private Integer    endorsmentFinancialId;

	    	    
	    //--- ENTITY DATA FIELDS 	    
	   	    
	
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="STARTDATE", nullable = false)     
		private Date  startDate;
		
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="EXPIRYDATE", nullable = false)     
		private Date  expiryDate;
		
	    
	    
		
		@Column(name="SUMINSURED")  
		private Double    sumInsured;
		
	    
		@Column(name="COMMISSIONBASEPREMIUM")  
		private Double    commissionBasePremium;
		

		 
		@Column(name="OTHERPREMIUM")  
		private Double    otherPremium;
		
	     
	  
		@Column(name="TOTALPREMIUM")  
		private Double    totalPremium;
		 
	      
		@Column(name="GST")  
		private Double    gst;
	     
		
		@Column(name="PREMIUMWITHGST")  
		private Double    premiumWithGst;
  
  
		  
		 
		   
		   
		   
		   
		 
		   
		      
		  
		  
  
   
  
   
	   

	    
}
