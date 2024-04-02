package com.maan.crm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.CampaignDetails;
import com.maan.crm.bean.CampaignDetailsId;

public interface CampaignDetailsRepository extends JpaRepository<CampaignDetails, CampaignDetailsId>, JpaSpecificationExecutor<CampaignDetails>{

	CampaignDetails findByMobileNumber(String mobileNumber);

	List<CampaignDetails> findByCampaignId(String campaignId);

	CampaignDetails findByMobileNumberAndCampaignId(String mobileNumber, String campaignId);



}
