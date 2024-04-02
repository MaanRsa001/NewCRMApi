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
@IdClass(PolicyPaymentDetailsId.class)
@Table(name="policy_payment_details")
public class PolicyPaymentDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
		@Column(name="PAYMENTDETAILSID",nullable=false)  
		private Integer    paymentdetailsid ; 

	    @Id
	    @Column(name="POLICYID",nullable=false) 
		private Integer    policyId;

	    	    
	    //--- ENTITY DATA FIELDS 	    
	   	    
		@Column(name="PAYMENTTYPE", length= 100)  
		private String paymentType;
	    
		@Column(name="PAYMENTREFNO", length =100)  
		private String paymentRefNo;
	    
		@Column(name="PAYMENTTYPEID")  
		private Integer    paymentTypeId;
		
		@Column(name="POLICYPAYMENTDETAILID")  
		private Integer    policyPaymentDetailId;
		
		@Column(name="BANKNAME", length=100)
		private String bankName; 
		
		
		
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="PAYMENTDATE", nullable = false)     
		private Date  paymentDate;

	    
	    @Column(name="PAYMENTAMOUNT", length=100)
		private String paymentAmount; 
	    
	    
	    @Column(name="REMARKS", length=100)
		private String remarks; 
	    
		
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="PAYMENTCOLLECTEDDATE", nullable = false)     
		private Date  paymentCollectedDate;
	    
		
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="ENTRYDATE", nullable = false)     
		private Date  entryDate;

		
	                         
	   

	    
}
