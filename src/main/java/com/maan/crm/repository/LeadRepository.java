package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.LeadDetailsId;

@Repository
public interface LeadRepository extends JpaRepository<LeadDetails, LeadDetailsId>{

	List<LeadDetails> OrderByLeadIdAsc();

	Page<LeadDetails> findByInsCompanyIdAndBranchCode(Pageable paging, String insId, 
			String branchCode);

	//View
	LeadDetails findByLeadId(String leadId);

	Object countByClientRefNo(String clientRefNo);

	// Lead View By Client Ref No
	
	List<LeadDetails> findByClientRefNoOrderByEntryDateDesc(String clientRefNo);

	// Bulk Edit
	List<LeadDetails> findByLeadIdIn(List<Integer> leadIds);

	List<LeadDetails> findAllByOrderByLeadIdDesc();

	Long countByClientRefNoAndStatusAndInsCompanyIdAndBranchCode(String clientRefNo, String string, String insCompanyId,String branchCode);
	
	List<LeadDetails> findByClientRefNoAndStatusAndInsCompanyIdAndBranchCode(String clientRefNo, String string, String insCompanyId,String branchCode);

	LeadDetails findByClientRefNoAndLeadId(String clientRefNo, String leadId);

	Page<LeadDetails> findByInsCompanyIdAndBranchCodeAndStatusOrderByEntryDateDesc(Pageable paging, String insCompanyId,
			String branchCode, String string);

	Page<LeadDetails> findByInsCompanyIdAndBranchCodeAndStatusOrderByUpdatedDateDesc(Pageable paging, String insuranceId,
			String branchCode, String status);
	// Lead With Count
	
	Page<LeadDetails> findByInsCompanyIdAndBranchCodeAndStatus(Pageable paging,
			String insId, String branchCode, String status);

	Long countByInsCompanyIdAndBranchCodeAndStatus(String insId, String branchCode,
			String status);

	List<LeadDetails> findByClientRefNo(String clientRefNo);

//	List<LeadDetails> findByClientRefNoAndStatusOrderByUpdatedDate(String clientRefNo, String status);

	//From Campaign
	LeadDetails findByClientRefNoOrderByClientRefNo(String clientRefNo);

	Long countByClientRefNoAndStatus(String clientRefNo, String string);



	Page<LeadDetails> findByClientRefNoAndStatusOrderByUpdatedDate(Pageable paging, String clientRefNo, String status);

	Page<LeadDetails> findByInsCompanyIdAndBranchCodeOrderByUpdatedDateDesc(Pageable paging, String insId,
			String branchCode);

	Long countByInsCompanyIdAndBranchCode(String insId, String branchCode);

	List<LeadDetails> findByClientRefNoOrderByUpdatedDateDesc(String clientRefNo);

	

}
