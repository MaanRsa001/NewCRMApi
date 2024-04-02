package com.maan.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.PolicyDetails;
import com.maan.crm.bean.PolicyDetailsId;

public interface PolicyDetailsRepository  extends JpaRepository<PolicyDetails, PolicyDetailsId>  , JpaSpecificationExecutor<PolicyDetails>{

	List<PolicyDetails> findAllByOrderByPolicyidDesc();

	List<PolicyDetails> findByPolicyid(int policyid);

	Optional<PolicyDetails> findByPolicyNumber(String policyNumber);
	
	Page<PolicyDetails> findByPolicyidAndProspectId(Pageable paging, Integer policyid, Integer prospectId);

	PolicyDetails findByPolicyidOrderByPolicyidAsc(Integer policyid);

	Page<PolicyDetails> findByCreatedByOrderByEntryDateDesc(Pageable paging, String createdBy);

	Page<PolicyDetails> findByInsCompanyIdAndBranchCode(Pageable paging, String insCompanyId, String branchCode);

	Long countByInsCompanyIdAndBranchCode(String insCompanyId, String branchCode);

	Long countByClientRefNo(String clientRefNo);

	List<PolicyDetails> findByClientRefNoAndInsCompanyIdAndBranchCode(String clientRefNo, String insCompanyId,
			String branchCode);

	Long countByClientRefNoAndInsCompanyIdAndBranchCode(String clientRefNo, String insId, String branchCode);

	List<PolicyDetails> findByClientRefNoOrderByEntryDateDesc(String clientRefNo);

	List<PolicyDetails> findByEnquiryIdOrderByEntryDateDesc(String enquiryId);

	List<PolicyDetails> findByQuoteNoOrderByEntryDateDesc(Integer valueOf);

	List<PolicyDetails> findByLeadIdOrderByEntryDateDesc(String leadId);


	
}
