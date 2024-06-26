/* 
*  Copyright (c) 2019. All right reserved
 * Created on 2022-05-14 ( Date ISO 2022-05-14 - Time 15:00:24 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

/*
 * Created on 2022-05-14 ( 15:00:24 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.maan.crm.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




/**
* Domain class for entity "ClaimLoginMaster"
*
* @author Telosys Tools Generator
*
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
@Builder
@IdClass(ClaimLoginMasterId.class)
@Table(name="LOGIN_MASTER")


public class ClaimLoginMaster implements Serializable {
 
private static final long serialVersionUID = 1L;
 
    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="LOGIN_ID", nullable=false, length=50)
    private String     loginId ;
    
    @Id
    @Column(name="COMPANY_ID", nullable=false, length=50)
    private String     companyId ;
    
    @Id
    @Column(name="AGENCY_CODE", nullable=false, length=50)
    private String     agencyCode ;
    
    @Id
    @Column(name="BRANCH_CODE", nullable=false, length=50)
    private String     branchCode ;
    
    @Id
    @Column(name="USERTYPE", nullable=false, length=50)
    private String     userType ;

    //--- ENTITY DATA FIELDS 
    @Column(name="PASSWORD", length=100)
    private String     password ;

    @Column(name="USERNAME", length=100)
    private String     username ;

    @Column(name="LPASS1", length=50)
    private String     lpass1 ;

    @Column(name="LPASS2", length=50)
    private String     lpass2 ;

    @Column(name="LPASS3", length=50)
    private String     lpass3 ;

    @Column(name="LPASS4", length=50)
    private String     lpass4 ;

    @Column(name="LPASS5", length=50)
    private String     lpass5 ;

    @Temporal(TemporalType.DATE)
    @Column(name="PASSDATE")
    private Date       passdate ;

    @Column(name="CREATED_BY", length=30)
    private String     createdBy ;

    @Column(name="REMARKS", length=100)
    private String     remarks ;

    @Column(name="STATUS")
    private String     status ;

    @Column(name="MENU_ID", length=2000)
    private String     menuId ;

    @Temporal(TemporalType.DATE)
    @Column(name="ENTRY_DATE")
    private Date       entryDate ;

    @Column(name="CORE_LOGIN_ID")
    private BigDecimal coreLoginId ;

    @Column(name="APP_ID", length=50)
    private String     appId ;

    @Column(name="PWD_COUNT", length=50)
    private String     pwdCount ;

    @Column(name="USER_MAIL", length=50)
    private String     userMail ;

    @Column(name="MOBILE_NUMBER", length=15)
    private String     mobileNumber ;

    @Column(name="LAST_MODIFIED_DATE")
    private Date     lastModifiedDate ;

    @Column(name="LAST_MODIFIED_BY", length=200)
    private String     lastModifiedBy ;


    //--- ENTITY LINKS ( RELATIONSHIP )


}



