package com.maan.crm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.CampaignDetails;
import com.maan.crm.bean.CampaignDetailsId;
import com.maan.crm.bean.CampaignMaster;
import com.maan.crm.bean.CampaignMasterId;
import com.maan.crm.bean.MailDetails;

public interface MailDetailsRepository extends JpaRepository<MailDetails, String>, JpaSpecificationExecutor<MailDetails>{



}
