package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.maan.crm.bean.LeadProduct;
import com.maan.crm.bean.LeadProductId;

public interface LeadProductRepository extends JpaRepository<LeadProduct, LeadProductId> {

	List<LeadProduct> findByLeadIdAndClientRefNo(String leadId, String clientRefNo);

	List<LeadProduct> findByLeadId(String leadId);

	Page<LeadProduct> findByInsCompanyIdAndBranchCodeOrderByUpdatedDateDesc(Pageable paging, String insId,
			String branchCode);

	Long countByInsCompanyIdAndBranchCode(String insId, String branchCode);

	List<LeadProduct> findByClientRefNoOrderByUpdatedDateDesc(String clientRefNo);

	Long countByClientRefNo(String clientRefNo);
	
	
}
