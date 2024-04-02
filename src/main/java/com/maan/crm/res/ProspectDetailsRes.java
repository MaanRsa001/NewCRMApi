package com.maan.crm.res;

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

public class ProspectDetailsRes  implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ProspectId")
	private Integer prospectId;
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
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EntryDate")
	private Date entryDate;
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
	@JsonProperty("CreatedBy")
	private String createdBy;
	@JsonProperty("AssignToGroupId")
	private Integer assignToGroupId;
	@JsonProperty("AssigntoUser")
	private String assigntoUser;
	@JsonProperty("AssignToUserId")
	private String assignToUserId;
	@JsonProperty("Remarks")
	private String remarks;
	@JsonProperty("ProsStatusId")
	private String prosStatusId;
	@JsonProperty("ProsStatusDesc")
	private String prosStatusDesc;
	
	// Client Details
	@JsonProperty("ClientRefNo")
    private String clientRefNo;  
	@JsonProperty("ClientType")
    private String clientType;
	@JsonProperty("ClientName")
    private String    clientName ;
	@JsonProperty("ReferenceName")
    private String referenceName;
	@JsonProperty("CRNo")
    private String  crno; 
	@JsonProperty("WillProvideReference")
    private String     willProvideReference ;
	@JsonProperty("IsGroupClient")
    private String isGroupClient;
	@JsonProperty("GroupClient")
    private String groupClient;
}
