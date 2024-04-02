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
@IdClass(CommissionMotorDetailsId.class)
@Table(name="COMMISSION_MOTOR")
public class CommissionMotorDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
		@Column(name="COMMISSIONID",nullable=false)  
		private Integer    commissionId ; 

	    @Id
	    @Column(name="AMENDID",nullable=false) 
		private Integer    amendId;

	    @Id
	    @Column(name="POLICYID",nullable=false) 
		private Integer    policyId;
	    
	    //--- ENTITY DATA FIELDS 	    
	   	    
	
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="COMMPROCESSEDDATE", nullable = false)     
		private Date  commProcessedDate;
		
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="COMMGENERATIONDATE", nullable = false)     
		private Date  commGenerationDate;
		
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	 	@Column(name="ENTRYDATE", nullable = false)     
	 	private Date  entryDate;
	    
		
		@Column(name="INSOWNDAMAGEADDONPER")  
		private Double    insOwnDamageAddOnPer;
		
	    
		@Column(name="INSOWNDAMAGEADDONAMOUNT")  
		private Double    insOwnDamageAddOnAmount;
		 
		@Column(name="INSTHIRDPARTYPER")  
		private Double    insThirdPartyPer;
		
		@Column(name="INSTHIRDPARTYAMOUNT")  
		private Double    insThirdPartyAmount;
		 
	      
		@Column(name="INSNETPER")  
		private Double    insNetPer;
	     
		
		@Column(name="INSNETAMOUNT")  
		private Double    insNetAmount;
  
		@Column(name="INSFLAT")  
		private Double    insFlat;
  
		@Column(name="INSTOTAL")  
		private Double    insTotal;
  
		@Column(name="INSGSTPER")  
		private Double    insGstPer;
  
		@Column(name="INSGSTAMOUNT")  
		private Double    insGstAmount;
  
		  
		@Column(name="INSTOTALWITHGST")  
		private Double    insTotalWithGST;
		@Column(name="PAYOUTOWNDAMAGEADDONPER")  
		private Double    payoutOwnDamageAddOnPer;
		@Column(name="PAYOUTOWNDAMAGEADDONAMOUNT")  
		private Double    payoutOwnDamageAddOnAmount;
		@Column(name="PAYOUTTHIRDPARTYPER")  
		private Double    payoutThirdPartyPer;
		@Column(name="PAYOUTTHIRDPARTYAMOUNT")  
		private Double    payoutThirdPartyAmount;
		@Column(name="PAYOUTNETPER")  
		private Double    payoutNetPer;
  
		   
		   
		@Column(name="PAYOUTNETAMOUNT")  
		private Double    payoutNetAmount;
		@Column(name="PAYOUTFLAT")  
		private Double    payoutFlat;
		@Column(name="PAYOUTTOTAL")  
		private Double    payoutTotal;
		@Column(name="PAYOUTGSTPER")  
		private Double    payoutGstPer;
		@Column(name="PAYOUTGSTAMOUNT")  
		private Double    payoutGstAmount;
		 
		 
		@Column(name="PAYOUTTOTALWITHGST")  
		private Double    payoutTotalWithGST;
		 
		
		
		 
	
  
   
	   

	    
}
