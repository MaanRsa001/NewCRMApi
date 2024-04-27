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
@IdClass(LeadDetailsId.class)
@Table(name="lead_details")
public class LeadDetails implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	 
	    //--- ENTITY PRIMARY KEY 
	    @Id
	    @Column(name="LEAD_ID", nullable=false)
	    private String     leadId;

	    @Id
	    @Column(name="CLIENT_REF_NO", nullable=false, length=50)
	    private String     clientRefNo ;
	    
	    @Id
	    @Column(name="Created_By", length=50)
	    private String     createdBy ;

	    @Id
	    @Column(name="Ins_Company_Id", length=50)
	    private String     insCompanyId ;

	    //--- ENTITY DATA FIELDS 
	    @Column(name="BUSINESSTYPE", length=100)
	    private String     businessType;
	    
	    @Column(name="BUSINESSTYPE_Id")
	    private Integer   businesstypeId ;


	    @Column(name="CLASS_DESC", length = 100)
	    private String classDesc ;


	    @Column(name="CLASS_ID")
	    private String classId ;


	    @Column(name="POLICYTYPE", length = 100)
	    private String 	policyType ;


	    @Column(name="POLICYTYPE_ID")
	    private Integer   policyTypeId ;

	    @Temporal(TemporalType.TIMESTAMP)
	  //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="LEAD_GEN_DATE")
	    private Date       leadGenDate ;

	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="DUE_DATE")
	    private Date       dueDate ;

	    @Column(name="BROKEN_POLICY", length = 100)
	    private String brokenPolicy ;

	    @Column(name="CLASSIFICATION", length = 100)
	    private String calssification ;

	    @Column(name="CLASSIFICATION_ID")
	    private Integer   classificationId ;

	    @Column(name="SOURCE", length = 100)
	    private String source ;

	    @Column(name="SOURCE_ID")
	    private Integer   sourceId ;

	    @Column(name="REFERREDBY", length = 100)
	    private String referredby ;

	    @Column(name="REFERREDBY_ID")
	    private Integer   referredbyId ;


	    @Column(name="OTHERTYPE", length = 100)
	    private String othertype ;

	    @Column(name="OTHERTYPE_ID")
	    private Integer   othertypeId ;

	    @Column(name="POS", length = 100)
	    private String pos ;

	    @Column(name="POS_ID")
	    private Integer   posId ;

	    @Column(name="REFERENCE_NAME", length = 100)
	    private String referenceName ;


	    @Column(name="ASSIGNTO_GROUP", length = 100)
	    private String assigntoGroup ;

	    @Column(name="ASSIGNTO_GROUP_ID")
	    private Integer   assigntoGroupId ;

	    @Column(name="ASSIGNTO_USER", length = 100)
	    private String assigntoUser ;

	    @Column(name="ASSIGNTO_USER_ID")
	    private String   assigntoUserId ;

	    @Column(name="REMARKS", length = 100)
	    private String remarks ;


	    @Temporal(TemporalType.TIMESTAMP)
	    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="ENTRY_DATE")
	    private Date       entryDate ;


	    @Column(name="STATUS", length = 1)
	    private String status ;
	    
	    @Column(name="Branch_Code", length=50)
	    private String     branchCode ;
	    
	    @Column(name="Region_Code", length=50)
	    private String     regionCode ;
	    
	    @Column(name="User_Type", length=50)
	    private String     userType ;

	    @Column(name="CLIENT_NAME", length=100)
	    private String     clientName;
	    
	    @Column(name="OLD_POLICY_NO", length=100)
	    private String    oldPolicyNo;
	    
	    @Column(name="UPDATED_BY", length=100)
	    private String    updatedBy;
	    
	    
	    
	    @Column(name="UPDATER_USERTYPE", length=100)
	    private String    updaterUsertype;
	    
	    @Column(name="VEH_CHASSIS_NO", length=100)
	    private String    vehChassisNo;
	    
	    @Column(name="LOCATION", length=100)
	    private String    location;
	    
	    @Column(name = "COVERNOTENUMBER")
		private String coverNoteNumber;

		@Column(name = "PROSPECTOWNER")
		private String prospectOwner;

		@Column(name = "PROSPECTOWNER_ID")
		private Integer prospectOwnerId;
		
		@Temporal(TemporalType.TIMESTAMP)
	    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	    @Column(name="UPDATED_DATE")
	    private Date       updatedDate ;
		
}
