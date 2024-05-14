package com.maan.crm.repository;

import com.maan.crm.bean.LeadProductDetails;
import com.maan.crm.bean.LeadProductDetailsId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadProductDetailsRepository extends JpaRepository<LeadProductDetails, LeadProductDetailsId> {

	List<LeadProductDetails> findByLeadId(String leadId);
    // You can add custom query methods if needed

	List<LeadProductDetails> findByLeadIdOrderBySequenceNo(String leadId);
}
