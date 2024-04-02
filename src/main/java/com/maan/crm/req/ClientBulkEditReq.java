package com.maan.crm.req;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientBulkEditReq {

	@JsonProperty("ClientRefNos")
	private List<Integer> clientRefNos ;
	
	@JsonProperty("Pos")
    private String pos;
    
	@JsonProperty("PosId")
    private String posId;
    
	@JsonProperty("Source")
    private String source;
   
	@JsonProperty("SourceId")
    private String sourceid;
	
	@JsonProperty("DefaultUser")
    private String defaultUser;
    
	@JsonProperty("DefaultUserId")
    private String defaultUserid;
	
	@JsonProperty("WillProvideReference")
    private String     willProvideReference ;
	
	@JsonProperty("WillProvideReferenceId")
    private Integer     willProvideReferenceId ;
	
	@JsonProperty("ReferenceName")
    private String     referenceName ;
	
	@JsonProperty("ReferenceNameId")
    private String     referenceNameId ;
}
