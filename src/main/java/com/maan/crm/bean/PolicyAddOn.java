package com.maan.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(PolicyAddOnId.class)
@Table(name="policy_add_on")
public class PolicyAddOn implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
		@Column(name="ADDONID",nullable=false)  
		private Integer    addOnid ; 

	    @Id
	    @Column(name="POLICYID",nullable=false) 
		private Integer    policyid;

	    	    
	    //--- ENTITY DATA FIELDS 	    
	   	    
		@Column(name="ADDONSOPTED", length= 100)  
		private String addOnsOpted;
	    
		@Column(name="ADDONSNOTOPTED", length =100)  
		private String addOnsNotOpted;
	    
		
}
