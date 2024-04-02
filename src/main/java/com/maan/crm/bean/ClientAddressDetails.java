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
@IdClass(ClientAddressDetailsId.class)
@Table( name ="client_address_details")
public class ClientAddressDetails implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//--- ENTITY PRIMARY KEY 
  
	@Id
    @Column(name="Client_Ref_No",nullable=false)
    private String clientRefNo;  

    @Id
    @Column(name= "Client_Address_Id", nullable=false)
    private Integer clientAddressId ; 
   
    //--- ENTITY DATA FIELDS 
    @Column(name="Address_Type_Id")
    private Integer addresTypeId;
    
    @Column(name="Address_Type", length=100)
    private String    addressType ;
    
    @Column(name="Address",length=100)
    private String address;
    
    @Column(name="Location",length=100)
    private String location;
    
    @Column(name="Lattitude" ,length=100)
    private String lattitude;
    
    @Column(name="Logitude",length=100)
    private String Logitude;

    @Column(name="STATUS", length=100)
    private String    status ;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="ENTRY_DATE")
    private Date      entryDate ;
    
    @Column(name="State_Id")
    private Integer stateId;
    
    @Column(name="State_Name",length=100)
    private String stateName;
    
    @Column(name="City_Id")
    private Integer cityId;
    
    @Column(name="City_Name",length=100)
    private String cityName;
    
    @Column(name="Mobile_No",length=10)
    private String mobileNo;
    
    @Column(name="Other_No",length=100)
    private String otherNo;  
    
    @Column(name="Other_Contact_Details",length=100)
    private String otherContactDetails; 
    
    @Column(name="Land_Line",length=10)
    private String landLine;
    
    @Column(name="Email_Id",length=100)
    private String emailId;

}