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
public class FollowUpDetailsRes implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Followupid")
	private Integer followupid;

	@JsonProperty("Clientid")
	private String clientid;

	@JsonProperty("Companyid")
	private String companyid;

	@JsonProperty("Loginid")
	private String loginid;

	@JsonProperty("Followupapplicableid")
	private Integer followupapplicableid;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("Nextfollowupdate")
	private Date nextfollowupdate;
	
	@JsonProperty("Nextfollowuptime")
	private String nextfollowuptime;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("Lastfollowupdate")
	private Date lastfollowupdate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("Laststatusupdatedate")
	private Date laststatusupdatedate;

	@JsonProperty("Laststatusupdatetime")
	private String laststatusupdatetime;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty("Entrydate")
	private Date entrydate;

	@JsonProperty("Followupapplicable")
	private String followupapplicable;
	
	@JsonProperty("REMARKS")
    private String remarks;
    
    @JsonProperty("FollowUpStatus")
    private String FollowUpStatus;
    
    @JsonProperty("Statusid")
    private Integer StatusId;
    
    @JsonProperty("Reason")
    private String reason;
    
    @JsonProperty("Reasonid")
    private Integer reasonId;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("Collecteddate")
    private Date collecteddate;

    @JsonProperty("Collectedtime")
    private String collectedtime;
    
    @JsonProperty("Followupconcludeddesc")
    private String followupconcludedDesc;
    
    @JsonProperty("Followupconcludedid")
    private Integer followupconcludedid;
    

}
