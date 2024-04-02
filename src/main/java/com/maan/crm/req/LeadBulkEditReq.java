package com.maan.crm.req;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

public class LeadBulkEditReq  implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("LeadId")
	private List<Integer> leadId ;
	
	@JsonProperty("ChangePos")
	private String changePos;
	@JsonProperty("ChangePosId")
	private String changePosId;
	
	@JsonProperty("ChangeClassification")
	private String changeClassification;
	@JsonProperty("ChangeClassificationId")
	private String ChangeClassificationId;
	
	@JsonProperty("ChangeAssigntoGroup")
	private String changeAssigntoGroup;
	@JsonProperty("ChangeAssigntoGroupId")
	private String changeAssignToGroupId;
	
	@JsonProperty("ChangeAssigntoUser")
	private String changeAssigntoUser;
	@JsonProperty("ChangeAssigntoUserId")
	private String changeAssigntoUserId;
	
	
	@JsonProperty("ChangeSource")
	private String changeSource;
	@JsonProperty("ChangeSourceId")
	private String changeSourceId;
	
	@JsonProperty("ChangeLocation")
	private String changeLocation;
	
	
	
	}
