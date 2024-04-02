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
@IdClass(DocumentUploadDetailsId.class)
@Table(name = "DOCUMENT_UPLOAD_DETAILS")

public class DocumentUploadDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLIENT_Id", nullable = false)
	private String clientid;

	@Id
	@Column(name = "DOCUMENT_REF", nullable = false)
	private Integer documentRef;

	@Id
	@Column(name = "DOC_TYPE_DESCRIPTION", nullable = false, length = 200)
	private String docTypeDescription;

	@Id
	@Column(name = "DOC_TYPE_ID", nullable = false)
	private Integer docTypeId;
	
	@Id
	@Column(name = "INS_COMPANY_ID", nullable = false)
    private String insCompanyId ;
    
	@Id
	@Column(name="DOC_APPLICABLE", nullable = false)
	private String docApplicable;
	
	@Id
	@Column(name="DOC_APPLICABLE_ID", nullable = false)
	private String docApplicableId;

	// --- ENTITY DATA FIELDS
	@Temporal(TemporalType.DATE)
	@Column(name = "UPLOADED_TIME")
	private Date uploadedTime;
	
	@Column(name = "FILE_PATH_ORGINAL")
	private String filePathOrginal;

	@Column(name = "FILE_PATH_BACKUP")
	private String filePathBackup;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "ORGINAL_FILE_NAME")
	private String orginalFileName;

	@Column(name = "CREATEDBY")
	private String createdby;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REQUESTED_BY")
	private String requestedBy;
	
	@Column(name = "UPLOADED_BY")
	private String uploadedBy;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@Column(name = "ENTRY_DATE")
	private Date entryDate;
	
}
