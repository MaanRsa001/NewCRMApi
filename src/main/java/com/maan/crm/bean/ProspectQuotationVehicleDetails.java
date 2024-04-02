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
@IdClass(ProspectQuotationVehicleDetailsId.class)
@Table(name="prospect_quotation_vechicle_details")
public class ProspectQuotationVehicleDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	    //--- ENTITY PRIMARY KEY 
	    @Id
	    @Column(name="QUOTATIONVECHID", nullable=false)
	    private Integer    	quotationVechid;

	    @Id
	    @Column(name="PROSPECTID", nullable=false)
	    private Integer     prospectId ;

	    	    
	    //--- ENTITY DATA FIELDS 	    
	    @Column(name="MAKEMODELVARIANT", length=100)
	    private String     makeModelVariant ;

	    @Column(name="REGISTRATIONNUMBER", length=100)
	    private String     registrationNumber ;

	    @Column(name="ENGINENUMBER", length=100)
	    private String     engineNumber ;

	    @Column(name="CHASSISNUMBER", length=100)
	    private String     chassisNumber ;

	    @Column(name="IDV", length=100)
	    private String    idv ;


	    @Column(name="ELECTRONICACCESSORYVALUE", length=100)
	    private String    electronicAccessoryValue ;
	    
	    @Column(name="OTHERTHAN_ELEC_ACC_VALUE_YN", length=100)
	    private String otherthanElecAccValueYn;
	    

	    @Column(name="OTHERTHAN_ELEC_ACC_VALUE")
	    private Integer    otherthanElecAccValue;
	    

	    @Column(name="CUBICCAPACITY")
	    private Integer    cubicCapacity;
	    
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="ENTRYDATE", nullable=false)
	    private Date       entryDate ;

	    
}
