package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.FollowUpDetails;
import com.maan.crm.bean.FollowUpDetailsId;



public interface FollowUpDetailsRepository  extends JpaRepository<FollowUpDetails, FollowUpDetailsId>  , JpaSpecificationExecutor<FollowUpDetails>{

	List<FollowUpDetails> findAllByOrderByFollowupidDesc();
	
	List<FollowUpDetails> findByCompanyid(String companyid);
	
	List<FollowUpDetails> findByCompanyidAndFollowUpStatus(String companyid,String FollowUpStatus);
		
	List<FollowUpDetails> findByCompanyidAndClientidAndFollowupapplicableAndFollowupapplicableid(String companyid, String clientid,String followupapplicable,String followupapplicableid);

	Page<FollowUpDetails> findByClientid(Pageable paging, String clientRefNo);
}
