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
@IdClass(PolicyNomineeDetailsId.class)
@Table(name="policy_nominee_details")
public class PolicyNomineeDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
		@Column(name="NOMINEEID",nullable=false)  
		private Integer    nomineeId ; 

	    @Id
	    @Column(name="POLICYID",nullable=false) 
		private Integer    policyId;

	    	    
	    //--- ENTITY DATA FIELDS 	    
	   	    
		@Column(name="NAME", length= 100)  
		private String name;
	    
		@Column(name="RELATION", length =100)  
		private String relation;
	    
		@Column(name="RELATIONID")  
		private Integer    relationId;
		
		@Temporal(TemporalType.DATE)
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
		@Column(name="DOB", nullable = false)     
		private Date  dOB;

	    @Column(name="GENDER", length=100)
		private String gender; 
		
	    @Column(name="GENDERID")  
		private Integer    genderId;
		
	    @Column(name="BENEFIT")  
		private Double    benefit;

	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="ENTRYDATE")
	    private Date entryDate;
	    
	   

	    
}
