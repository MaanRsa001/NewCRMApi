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
@IdClass(PolicyRiderDetailsId.class)
@Table( name ="POLICY_RIDER_DETAILS")
public class PolicyRiderDetails implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//--- ENTITY PRIMARY KEY 
  
	@Id
    @Column(name="RIDER_ID",nullable=false, length=50)
    private Integer riderId;  
	
    @Id
    @Column(name="POLICY_ID", nullable=false, length=50)
    private Integer    policyId ;
    

    //--- ENTITY DATA FIELDS 
    @Column(name= "RIDER_NAME", length=100)
    private String riderName ;
    
    @Column(name="SUM_ASSURED")
    private Double sumAssured;
    
    @Column(name="PREMIUM")
    private Double    premium ;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="ENTRY_DATE")
    private Date entryDate;
    
    @Column(name="STATUS", length=10)
    private String     status;
   
}
