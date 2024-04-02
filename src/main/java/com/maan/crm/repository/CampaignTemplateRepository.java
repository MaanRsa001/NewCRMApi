package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.CampaignTemplate;
import com.maan.crm.bean.CampaignTemplateId;
import com.maan.crm.bean.CrmListITemValue;

@Repository
public interface CampaignTemplateRepository  extends JpaRepository<CampaignTemplate, CampaignTemplateId> , JpaSpecificationExecutor<CampaignTemplate>{

	List<CampaignTemplate> findByStatusOrderByFieldIdAsc(String status);

}
