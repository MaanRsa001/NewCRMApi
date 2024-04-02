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
@IdClass(CampaignOfferDetailsId.class)
@Table(name="campaign_offer_details")
public class CampaignOfferDetails implements Serializable {

	@Id
	@Column(name="CAMPAIGN_ID",length=100, nullable=false)
	private String campaignId;
	
	@Id
	@Column(name="OFFER_ID",length=100, nullable=false)
	private Integer offerId;
	
	@Column(name="OFFER_DESC", length =100, nullable=false)
	private String offerDesc;
	
	@Column(name="STATUS", length=100, nullable=false)
	private String status;
	
	
	@Temporal(TemporalType.DATE)
    @Column(name="ENTRY_DATE")
    private Date       entryDate ;
	
	
}
