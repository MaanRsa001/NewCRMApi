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
@IdClass(ClientDetailsId.class)
@Table(name = "client_details")
public class ClientDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --- ENTITY PRIMARY KEY

	@Id
	@Column(name = "CLIENT_REF_NO", nullable = false, length = 50)
	private String clientRefNo;


	@Column(name = "CRNO",length = 100)
	private String crno;

	// --- ENTITY DATA FIELDS
	@Column(name = "CLIENT_TYPE", nullable = false, length = 50)
	private String clientType;

	@Column(name = "CLIENT_TYPE_ID", nullable = false)
	private Integer clientTypeId;

	@Column(name = "CLIENT_NAME", nullable = false, length = 100)
	private String clientName;

	@Column(name = "GST_IN",  length = 100)
	private String gstIn;

	@Column(name = "CREATED_BY", length = 50)
	private String createdBy;

	@Column(name = "INS_COMPANY_ID", length = 50)
	private String insCompanyId;

	@Column(name = "TITLE", length = 100)
	private String title;

	@Column(name = "TITLE_ID")
	private String titleId;

	@Column(name = "GENDER", length = 100)
	private String gender;

	@Column(name = "GENDER_ID")
	private Integer genderId;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Column(name = "AGE")
	private Integer age;

	@Column(name = "MARITAL_STATUS", length = 100)
	private String matirialStatus;

	@Column(name = "MARITAL_STATUS_ID")
	private Integer maritalStatusId;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_ANNIVERSARY")
	private Date dateOfAnniversary;

	@Column(name = "OCCUPATION_ID")
	private Integer occupationId;

	@Column(name = "OCCUPATION", length = 100)
	private String occupation;

	@Column(name = "DESIGNATION", length = 100)
	private String designation;

	@Column(name = "ANNUAL_INCOME", length = 100)
	private String annualIncome;

	@Column(name = "ANNUAL_INCOME_ID")
	private Integer annualIncomeId;

	@Column(name = "PASSPORT_NO")
	private String passportNo;

	@Column(name = "DMATID")
	private String dmatid;

	@Column(name = "DEFAULT_USER", length = 100)
	private String defaultUser;

	@Column(name = "DEFAULT_USER_ID")
	private Integer defaultUserId;

	@Column(name = "IS_GROUP_CLIENT", length = 100)
	private String isGroupClient;

	@Column(name = "IS_GROUP_CLIENT_ID")
	private Integer isGroupClientId;

	@Column(name = "GROUP_CLIENT_ID")
	private Integer groupClientId;

	@Column(name = "GROUP_CLIENT", length = 100)
	private String groupClient;

	@Column(name = "POS", length = 100)
	private String pos;

	@Column(name = "POS_Id")
	private Integer posId;

	@Column(name = "SOURCE", length = 100)
	private String source;

	@Column(name = "SOURCE_ID")
	private Integer sourceId;

	@Column(name = "REFERENCE_NAME", length = 100)
	private String referenceName;

	@Column(name = "REFERENCE_NAME_ID")
	private Integer referenceNameId;

	@Column(name = "WILL_PROVIDE_REF_ID")
	private Integer willProvideRefId;

	@Column(name = "WILL_PROVIDE_REFERENCE", length = 100)
	private String willProvideReference;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENTRY_DATE")
	private Date entryDate;

	@Column(name = "STATUS", length = 10)
	private String status;

	@Column(name = "COMPANY_TYPE", length = 100)
	private String companyType;

	@Column(name = "COMPANY_TYPE_ID")
	private Integer companyTypeId;

	@Column(name = "BRANCH_CODE", length = 50)
	private String branchCode;

	@Column(name = "REGION_CODE", length = 50)
	private String regionCode;

	@Column(name = "ADDRESS_YN", length = 5)
	private String addressYn;

	@Column(name = "USER_TYPE", length = 50)
	private String userType;

	
	@Column(name = "STATUSS", length = 10)
	private String statuss;
	
	@Column(name = "STATUS_DESCRIPTION", length = 10)
	private String statusDescription;

	@Column(name = "MOBILE_NUMBER", length = 10)
	private String mobileNumber;

	@Column(name = "ALTERNATIVE_NUMBER", length = 10)
	private String alternativeNumber;

	@Column(name = "EMAIL_ID", length = 50)
	private String emailId;

	@Column(name = "REFERENCER_DETAILSYN", length = 10)
	private String referencerDetailsYn;
	
	@Column(name = "REFERENCER_MOBILE", length = 10)
	private String referencerMobile;
	

	@Column(name = "REFERENCER_EMAIL", length = 50)
	private String referencerEmail;


	@Column(name = "YOUR_REFERENCE_NAME", length = 100)
	private String yourReferenceName;
	

	@Column(name = "YOUR_REFERENCE_MAILID", length = 100)
	private String yourReferenceMailid;
	

	@Column(name = "YOUR_REFERENCE_MOBILE", length = 10)
	private String yourReferenceMobile;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_VISITED_DATE")
	private Date lastVisitedDate;
	

}
