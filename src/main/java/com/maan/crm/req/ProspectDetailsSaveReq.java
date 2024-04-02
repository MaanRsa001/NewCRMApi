package com.maan.crm.req;

import java.io.Serializable;
import java.util.Date;

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

public class ProspectDetailsSaveReq  implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ProspectId")
	private Integer prospectId;
	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	@JsonProperty("VehChassisNo")
	private String vehChassisNo;
	@JsonProperty("BusinessType")
	private String businessType;
	@JsonProperty("BusinessTypeId")
	private Integer businessTypeId;
	@JsonProperty("Classs")
	private String classs;
	@JsonProperty("ClassId")
	private Integer classId;
	@JsonProperty("PolicyType")
	private String policyType;
	@JsonProperty("PolicyTypeId")
	private Integer policyTypeId;
	@JsonProperty("Source")
	private String source;
	@JsonProperty("SourceId")
	private Integer sourceId;
	@JsonProperty("Pos")
	private String pos;
	@JsonProperty("PosId")
	private Integer posId;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("GenerationDate")
	private Date generationDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("DueDate")
	private Date dueDate;
	@JsonProperty("Classification")
	private String classification;
	@JsonProperty("ClassificationId")
	private Integer classificationId;
	@JsonProperty("Location")
	private String location;
	@JsonProperty("CoverNoteNumber")
	private String coverNoteNumber;
	@JsonProperty("ProspectOwner")
	private String prospectOwner;
	@JsonProperty("ProspectOwnerId")
	private Integer prospectOwnerId;
	@JsonProperty("AssigntoGroup")
	private String assigntoGroup;
	@JsonProperty("AssigntoGroupId")
	private Integer assignToGroupId;
	@JsonProperty("AssigntoUser")
	private String assigntoUser;
	@JsonProperty("AssigntoUserId")
	private Integer assigntoUserId;
	@JsonProperty("Remarks")
	private String remarks;
	@JsonProperty("LeadId")
	private Integer leadId;
	@JsonProperty("ProsStatusId")
	private Integer prosStatusId;
	@JsonProperty("ProsStatusDesc")
	private String prosStatusDesc;
	@JsonProperty("CreatedBy")
	private String createdBy;
	@JsonProperty("OldPolicyNo")
	private String oldPolicyNo;
}
