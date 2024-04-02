package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.EnquiryDetails;
import com.maan.crm.bean.EnquiryDetailsId;
import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.LeadDetailsId;

@Repository
public interface EnquiryRepository extends JpaRepository<EnquiryDetails, EnquiryDetailsId>{

	EnquiryDetails findByEnquiryId(String enqId);

	Long countByEnqStatusAndBranchCodeAndInsCompanyIdAndAllotedUw(String subStatusCode, String branchCode,
			String insCompanyId, String createdBy);

	Long countByEnqStatusAndBranchCodeAndInsCompanyIdAndCreatedBy(String subStatusCode, String branchCode,
			String insCompanyId, String createdBy);

	Page<EnquiryDetails> findByEnqStatusAndBranchCodeAndInsCompanyIdAndAllotedUwOrderByUpdatedDateDesc(Pageable paging ,String subStatusCode,
			String branchCode, String insCompanyId, String createdBy);

	Page<EnquiryDetails> findByEnqStatusAndBranchCodeAndInsCompanyIdAndCreatedUsertypeOrderByUpdatedDateDesc(Pageable paging ,String subStatusCode,
			String branchCode, String insCompanyId, String userType);

	Page<EnquiryDetails> findByEnqStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(Pageable paging , String subStatusCode, String branchCode,
			String insCompanyId);

	Page<EnquiryDetails> findByLeadIdOrderByUpdatedDateDesc(Pageable paging, String leadId);

	Long countByEnqStatusAndBranchCodeAndInsCompanyIdAndCreatedUsertypeOrderByUpdatedDateDesc(String subStatusCode,
			String branchCode, String insCompanyId, String userType);

	Long countByEnqStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(String subStatusCode, String branchCode,
			String insCompanyId);

	List<EnquiryDetails> findByClientRefNoOrderByUpdatedDateDesc(String clientRefNo);

	List<EnquiryDetails> findByLeadIdOrderByUpdatedDateDesc(String leadId);

}
