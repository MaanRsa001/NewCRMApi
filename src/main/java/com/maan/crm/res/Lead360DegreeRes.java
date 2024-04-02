package com.maan.crm.res;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Lead360DegreeRes  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("ClientName")
	private String clientName;
	
	@JsonProperty("ClientTypeId")
	private String clientTypeId;
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("EmailId")
	private String emailId;
	
	@JsonProperty("ReferenceNameId")
	private Integer referenceNameId;
	
	@JsonProperty("MobileNumber")
	private String mobileNumber;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
	
	@JsonProperty("LeadId")
	private Integer leadId;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
	@JsonProperty("EntryDate")
	private Date entryDate;
	

	@JsonProperty("Status")
	private String status;

	@JsonProperty("ClientType")
	private String clientType;
	
	@JsonProperty("ReferenceName")
	private String referenceName;
	
	@JsonProperty("ClassId")
	private String classId;
	
	@JsonProperty("ClassDesc")
	private String classDesc;

	@JsonProperty("BusinessTypeId")
	private Integer businessTypeId;
	
	@JsonProperty("BusinessType")
	private String businessType;

	@JsonProperty("PolicyTypeId")
	private Integer policyTypeId;
	
	@JsonProperty("PolicyType")
	private String policyType;
}
