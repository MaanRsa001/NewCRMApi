package com.maan.crm.res;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Client360DegreeRes  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("ClientName")
	private String clientName;
	
	@JsonProperty("ClientTypeId")
	private String clientTypeId;
	

	@JsonProperty("ClientType")
	private String clientType;
	
	@JsonProperty("ClientRefNo")
	private String clientRefNo;
	
	@JsonProperty("EmailId")
	private String emailId;
	
	@JsonProperty("ReferenceNameId")
	private Integer referenceNameId;
	
	@JsonProperty("ReferenceName")
	private String referenceName;
	
	
	@JsonProperty("MobileNumber")
	private String mobileNumber;
	
	@JsonProperty("CreatedBy")
	private String createdBy;
}
