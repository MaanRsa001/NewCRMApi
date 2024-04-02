package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.CampaignTemplateForm;
import com.maan.crm.bean.CampaignTemplateFormId;

public interface CampaignTemplateFormRepository extends JpaRepository<CampaignTemplateForm, CampaignTemplateFormId>, JpaSpecificationExecutor<CampaignTemplateForm>{

	List<CampaignTemplateForm> findByCampaignIdOrderBySnoAsc(String campId);



}
