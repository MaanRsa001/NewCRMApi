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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Domain class for entity "ClaimLoginUserDetails"
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
@IdClass(ProspectDetailsId.class)
@Table(name = "PROSPECT_DETAILS")

public class ProspectDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	// --- ENTITY PRIMARY KEY
	@Id
	@Column(name = "PROSPECTID", nullable = false)
	private Integer prospectid;

	@Id
	@Column(name = "CLIENT_REF_NO", nullable = false)
	private String clientRefNo;
	
	@Column(name = "LEAD_ID", nullable = false)
	private String leadId;
	
	@Column(name = "QUOTE_NO")
	private String quoteNo;

	@Column(name = "VEH_CHASSIS_NO")
	private String vehChassisNo;

	@Column(name = "BUSINESSTYPE")
	private String businessType;

	@Column(name = "BUSINESSTYPE_ID")
	private Integer businessTypeId;

	@Column(name = "CLASSS")
	private String classs;

	@Column(name = "CLASSID")
	private Integer classId;

	@Column(name = "POLICYTYPE")
	private String policyType;

	@Column(name = "POLICYTYPE_ID")
	private Integer policyTypeId;

	@Column(name = "SOURCE")
	private String source;

	@Column(name = "SOURCE_ID")
	private Integer sourceId;

	@Column(name = "POS")
	private String pos;

	@Column(name = "POSID")
	private Integer posId;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "GENERATIONDATE")
	private Date generationDate;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "ENTRYDATE")
	private Date entryDate;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "DUEDATE")
	private Date dueDate;

	@Column(name = "CLASSIFICATION")
	private String classification;

	@Column(name = "CLASSIFICATION_ID")
	private Integer classificationId;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "COVERNOTENUMBER")
	private String coverNoteNumber;

	@Column(name = "PROSPECTOWNER")
	private String prospectOwner;

	@Column(name = "PROSPECTOWNER_ID")
	private Integer prospectOwnerId;

	@Column(name = "ASSIGNTOGROUP")
	private String assigntoGroup;

	@Column(name = "ASSIGNTOUSER")
	private String assigntoUser;

	@Column(name = "ASSIGN_TO_USER_ID")
	private Integer assigntoUserId;

	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "PROS_STATUS_ID")
	private Integer prosStatusId;
	
	@Column(name = "PROS_STATUS_DESC")
	private String prosStatusDesc;
	
	@Column(name="ASSIGN_TO_GROUP_ID")
	private Integer assignToGroupId;


	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "OLD_POLICY_NO")
	private String oldPolicyNo;
}
