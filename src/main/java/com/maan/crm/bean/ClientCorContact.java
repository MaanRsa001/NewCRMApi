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
@IdClass(ClientCorContactId.class)
@Table( name ="client_cor_contact_person_details")
public class ClientCorContact implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//--- ENTITY PRIMARY KEY 
  
	@Id
    @Column(name="Client_Ref_No",nullable=false)
    private String clientRefNo;  

    @Id
    @Column(name= "Clientcor_Contact_Id", nullable=false)
    private Integer clientcorContactId ;
    
   
    //--- ENTITY DATA FIELDS 
    @Column(name="Contact_Person_Name", length=100)
    private String    contactPersonName ;

    @Column(name="STATUS", length=100)
    private String    status ;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="ENTRY_DATE")
    private Date      entryDate ;
 
    @Column(name="Contact_Person_Desigination")
    private String contactPersonDesigination;
    
    @Column(name="Mobile_No")
    private String mobileNo;
    
    @Column(name="Email")
    private String email;

}