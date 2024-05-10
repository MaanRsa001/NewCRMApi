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

import org.hibernate.annotations.CreationTimestamp;

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
@IdClass(FollowUpDetailsId.class)
@Table( name ="FOLLOWUP_DETAILS")
public class FollowUpDetails implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//--- ENTITY PRIMARY KEY 
  
    
	@Id
    @Column(name="FOLLOWUPID",nullable=false)
    private Integer followupid;

	@Id
    @Column(name="CLIENTID",nullable=false)
    private String clientid;

	@Id
    @Column(name="COMPANYID",nullable=false)
    private String companyid;
	
	@Id
    @Column(name="LOGINID",nullable=false)
    private String loginid;

	@Id
    @Column(name="FOLLOWUPAPPLICABLEID",nullable=false)
    private String followupapplicableid;
	
	@Column(name="PROSPECT_ID")
	private Integer prospectid ;
    
    @Column(name="NEXTFOLLOWUPTIME")
    private String      nextfollowuptime ;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="NEXTFOLLOWUPDATE")
    private Date      nextfollowupdate;
    
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="LASTFOLLOWUPDATE")
    private Date      lastfollowupdate ;
    
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="LASTSTATUSUPDATEDATE")
    private Date      laststatusupdatedate;
    
    @Column(name="LASTSTATUSUPDATETIME")
    private String      laststatusupdatetime ;
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="ENTRYDATE")
    private Date      entrydate;
    
    @Column(name="FOLLOWUPAPPLICABLE")
    private String followupapplicable;
    
    @Column(name="REMARKS")
    private String remarks;
    
    @Column(name="FOLLOWUPSTATUS")
    private String followUpStatus;
    
    @Column(name="STATUSID")
    private Integer StatusId;
    
    @Column(name="REASON")
    private String reason;
    
    @Column(name="REASONID")
    private Integer reasonId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "en-IN", timezone = "Asia/Calcutta")
    @Column(name="COLLECTEDDATE")
    private Date collecteddate;
    
    @Column(name="COLLECTEDTIME")
    private String collectedtime;
    
    @Column(name="FOLLOWUPCONCLUDEDDESC")
    private String followupconcludedDesc;
    
    @Column(name="FOLLOWUPCONCLUDEDID")
    private Integer followupconcludedid;
    
    @Column(name="FOLLOWUPHOLDER")
    private String followupholder;
    
    @Column(name="FOLLOWUPHOLDERTYPE")
    private String followupholdertype;
    
    @Column(name="COLLECTEDBY")
    private String collectedby;
}
