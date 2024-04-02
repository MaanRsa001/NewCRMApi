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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CampaignTemplateFormId.class)
@Table(name="campaign_template_form")
public class CampaignTemplateForm implements Serializable {

	@Id
	@Column(name="CAMPAIGN_ID",length=100, nullable=false)
	private String campaignId;
	
	@Id
	@Column(name="SNo",length=100, nullable=false)
	private Integer sno;
	
	@Column(name="FIELD_ID", length =100, nullable=false)
	private Integer fieldId;
	
	@Column(name="FIELD_NAME", length =100, nullable=false)
	private String fieldName;
	
	
	@Column(name="DISPLAY_NAME",nullable=false, length=10)
	private String dispalyName;
	

	@Column(name="STATUS", length=100, nullable=false)
	private String status;
	
	
	@Temporal(TemporalType.DATE)
    @Column(name="ENTRY_DATE")
    private Date       entryDate ;
	
	
}
