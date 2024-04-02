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
@IdClass(ClientMemberDetailsId.class)
@Table( name ="client_member_details")
public class ClientMemberDetails implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//--- ENTITY PRIMARY KEY 
  
	@Id
    @Column(name="Client_Ref_No",nullable=false)
    private String clientRefNo;  

    @Id
    @Column(name= "Client_Member_Id", nullable=false)
    private Integer clientMemberId ; 
   
    //--- ENTITY DATA FIELDS 
    @Column(name="Member_Name", length=100)
    private String    memberName ;
    
    @Column(name="Relation",length=100)
    private String relation;
    
    @Column(name="Gender" ,length=100)
    private String gender;
    
    @Column(name="Gender_Id")
    private Integer genderId;
    
    @Column(name="Age")
    private Integer age;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="Date_Of_Birth")
    private Date dateOfBirth ;

    @Column(name="STATUS", length=100)
    private String    status ;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="ENTRY_DATE")
    private Date      entryDate ;

}