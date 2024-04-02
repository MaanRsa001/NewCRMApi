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
@IdClass(EndorsementPaymentDetailsId.class)
@Table(name="endorsement_payment_details")
public class EndorsementPaymentDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
		@Column(name="ENDORSEMENTID",nullable=false)  
		private Integer    endorsementId ; 

	    @Id
	    @Column(name="ENDORSEMENTPAYMENTID",nullable=false) 
		private Integer    endorsementPaymentId;

	    	    
	    //--- ENTITY DATA FIELDS 	    
	   	    
		@Column(name="PAYMENTTYPE", length= 200)  
		private String paymentType;
	    
		
		@Column(name="PAYMENTTYPEID")  
		private Integer    paymentTypeId;
		
		

		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="PAYMENTDATE", nullable = false)     
		private Date  paymentDate;
		
	    @Column(name="PAYMENTREFNO", length= 200)  
		private String paymentRefNo;
	    
		
	    
		@Column(name="BANKNAME", length= 200)  
		private String bankName;
	    
	    
		@Column(name="PAYMENTAMOUNT")  
		private Double paymentAmount;
	    
		@Column(name="REMARKS", length =200)  
		private String remarks;
	      
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
			@Column(name="PAYMENTCOLLECTEDDATE", nullable = false)     
			private Date  paymentCollectedDate;
		
  
	    
}
