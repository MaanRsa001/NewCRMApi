package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.CampaignOfferDetails;
import com.maan.crm.bean.CampaignOfferDetailsId;
import com.maan.crm.bean.CampaignTemplateForm;
import com.maan.crm.bean.CampaignTemplateFormId;

public interface CampaignOfferDetailsRepository extends JpaRepository<CampaignOfferDetails, CampaignOfferDetailsId>, JpaSpecificationExecutor<CampaignOfferDetails>{

	List<CampaignOfferDetails> findByCampaignIdOrderByOfferIdAsc(String campId);



}
