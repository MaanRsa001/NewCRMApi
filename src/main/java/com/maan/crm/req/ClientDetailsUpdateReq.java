package com.maan.crm.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClientDetailsUpdateReq {
	
	@JsonProperty("CompanyType")
    private String     companyType ;
    
	@JsonProperty("CompanyTypeId")
    private String     companyTypeId ;
	
	@JsonProperty("DefaultUser")
    private String defaultUser;
    
	@JsonProperty("DefaultUserId")
    private String defaultUserid;
	
	@JsonProperty("GroupClient")
    private String groupClient;
	
	@JsonProperty("GroupClientId")
    private String groupClientId;
	
	@JsonProperty("IsGroupClient")
    private String isGroupClient;
    
	@JsonProperty("IsGroupClientId")
    private String isGroupClientid;
	
	@JsonProperty("Pos")
    private String pos;
    
	@JsonProperty("PosId")
    private String posId;
    /*
	@JsonProperty("Source")
    private String source;
   
	@JsonProperty("SourceId")
    private String sourceid;
	
	@JsonProperty("WillProvideReference")
    private String     willProvideReference ;
	
	@JsonProperty("WillProvideReferenceId")
    private String     willProvideReferenceId ;
		
	@JsonProperty("ReferenceName")
    private String     referenceName ;
	
	@JsonProperty("ReferenceNameId")
    private String     referenceNameId ;
*/
}
