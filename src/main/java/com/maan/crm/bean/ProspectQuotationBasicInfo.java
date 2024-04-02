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
@IdClass(ProspectQuotationBasicInfoId.class)
@Table(name="prospect_quotation_basic_info")
public class ProspectQuotationBasicInfo implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	 
	    //--- ENTITY PRIMARY KEY 
	    @Id
	    @Column(name="QUOTATIONID", nullable=false)
	    private Integer     quotationId ;

	    @Id
	    @Column(name="PROSPECTID", nullable=false)
	    private Integer     prospectId ;

	    //--- ENTITY DATA FIELDS 	    
	    @Column(name="CLASSS", length=100)
	    private String     classs ;

   
	    @Column(name="POLICYTYPE ", length=100)
	    private String   policyType ;


	    @Column(name="CLIENTNAME", length = 100)
	    private String clientName ;


	    @Column(name="OLDPOLICYNO", length = 100)
	    private String oldPolicyNo ;

	    
	    @Column(name="PREMIUMCOVERTYPE", length = 100)
	    private String premiumCoverType ;

	    @Column(name="PREMIUMCOVERTYPEID")
	    private Integer premiumCoverTypeId ;

	    
	    @Column(name="POLICYTERM", length = 100)
	    private String policyTerm ;

	    @Column(name="POLICYTERMID")
	    private Integer policyTermId ;

	    
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="STARTDATE", nullable=false)
	    private Date       startDate ;

	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="EXPIRYDATE", nullable=false)
	    private Date       expiryDate ;

	    
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="ENTRYDATE", nullable=false)
	    private Date       entryDate ;

	    
}
