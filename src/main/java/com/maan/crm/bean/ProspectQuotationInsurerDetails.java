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
@IdClass(ProspectQuotationInsurerDetailsId.class)
@Table(name="prospect_quotation_insurer_details")
public class ProspectQuotationInsurerDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	 
	    //--- ENTITY PRIMARY KEY 
	    @Id
	    @Column(name="INSURERID", nullable=false)
	    private Integer     insurerId ;

	    @Id
	    @Column(name="INSURER",length=100, nullable=false)
	    private String     insurer ;

	    @Id
	    @Column(name="PROSPECTID", nullable=false)
	    private Integer     prospectId ;

	    @Id
	    @Column(name="INSURERDETAILSID", nullable=false)
	    private Integer     insurerDetailsId ;

	    
	    //--- ENTITY DATA FIELDS 	    
	    @Column(name="INSURERBRANCH", length=100)
	    private String     insurerBranch ;

   
	    
	    @Column(name="INSURERBRANCHID")
	    private Integer insurerBranchId ;

	    @Column(name="INSURANCEPLAN", length=100)
	    private String     insurancePlan ;

	    
	   
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="ENTRYDATE", nullable=false)
	    private Date       entryDate ;

	    
}
