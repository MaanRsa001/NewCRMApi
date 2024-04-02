package com.maan.crm.req;

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
public class EndorsementGeneralDetailsSaveReq {


	
	@JsonProperty("EndorsementId")
	private int endorsementId;
	

	@JsonProperty("PolicyId")
	private int policyId;

	
	@JsonProperty("EndorsementType")
	private String endorsementType;
	
	@JsonProperty("EndorsementTypeId")
	private int endorsementTypeId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("ReceivedDate")
	private Date receivedDate;
	
	@JsonProperty("EndorsementStatus")
	private String endorsementStatus;
	
	@JsonProperty("EndorsementStatusId")
	private int endorsementStatusId;
	
	@JsonProperty("EndorsementNumber")
	private String endorsementNumber;
	
	@JsonProperty("EndorsementReason1")
	private String endorsementReason1;

	
	@JsonProperty("EndorsementReason1Id")
	private int endorsementReason1Id;

	@JsonProperty("EndorsementReason2")
	private String endorsementReason2;

	@JsonProperty("EndorsementReason2Id")
	private int endorsementReason2Id;

	@JsonProperty("EndorsementReason3")
	private String endorsementReason3;

	@JsonProperty("EndorsementReason3Id")
	private int endorsementReason3Id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("EffectiveDate")
	private Date effectiveDate;

	@JsonProperty("EndorsementDescription")
	private String endorsementDescription;

	@JsonProperty("EndorsementComment")
	private String endorsementComment;
	
	
	
	
	
	                                 
}
