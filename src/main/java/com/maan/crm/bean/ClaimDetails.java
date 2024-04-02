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
@IdClass(ClaimDetailsId.class)
@Table( name ="CLAIM_DETAILS")
public class ClaimDetails implements Serializable  {

	private static final long serialVersionUID = 1L;
  
	@Id
	@Column(name="CLAIMID",nullable=false)
    private Integer claimid;
	
	@Id
	@Column(name="POLICYID",nullable=false)
    private Integer policyId;
	
	@Column(name="CLAIMREFERENCENO")
    private String claimReferenceNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CLAIMRECEIPTDATE")
    private Date claimReceiptDate;  
	
	@Column(name="ASSETIDENTIFICATIONNO")
    private String assetIdentificationNo;
	
	@Column(name="ESTIMATEDLOSS")
    private Double estimatedLoss;
	
	@Column(name="LOSSLOCATION")
    private String lossLocation;
	
	@Temporal(TemporalType.DATE)
    @Column(name="DATEOFLOSS")
	private Date dateOfLoss;  
    
	@Column(name="BRIEFDESCRIPTIONOFLOSS")
	private Double briefDescriptionOfLoss;  
    
	@Column(name="SURVEYORNAME")
	private String surveyorName;  
    
	@Column(name="SURVEYORCONTACTPERSON")
	private String surveyorContactPerson;  
    
	@Column(name="SURVEYORCONTACTDETAILS")
	private String surveyorContactDetails;  
    
	@Column(name="SITECONTACTDETAILS")
	private String siteContactDetails;  

    
}