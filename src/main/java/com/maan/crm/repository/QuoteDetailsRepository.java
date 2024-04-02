package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.EnquiryDetails;
import com.maan.crm.bean.QuoteDetails;
import com.maan.crm.bean.QuoteDetailsId;

@Repository
public interface QuoteDetailsRepository extends JpaRepository<QuoteDetails, QuoteDetailsId>{

	
	List<QuoteDetails> findByClientRefNoAndLeadId(String clientRefNo, String leadId);

	QuoteDetails findByReferenceNo(String clientRefNo);

	List<QuoteDetails> findByClientRefNo(String clientRefNo);


	QuoteDetails findByQuoteNo(String enqId);
/*
	List<QuoteDetails> findByQuoteStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(
			String subStatusCode, String branchCode, String insCompanyId);

	List<QuoteDetails> findByQuoteStatusAndBranchCodeAndInsCompanyIdAndCreatedUsertypeOrderByUpdatedDateDesc(
			String subStatusCode, String branchCode, String insCompanyId, String userType);

	List<QuoteDetails> findByQuoteStatusAndBranchCodeAndInsCompanyIdAndEnqCreaterUsertypeOrderByUpdatedDateDesc(
			String subStatusCode, String branchCode, String insCompanyId, String userType);

	List<QuoteDetails> findByQuoteStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(String subStatusCode,
			String branchCode, String insCompanyId);
*/
	Page<QuoteDetails> findByQuoteStatusAndBranchCodeAndInsCompanyIdAndCreatedUsertypeOrderByUpdatedDateDesc(
			Pageable paging, String subStatusCode, String branchCode, String insCompanyId, String userType);

	Page<QuoteDetails> findByQuoteStatusAndBranchCodeAndInsCompanyIdAndEnqCreaterUsertypeOrderByUpdatedDateDesc(
			Pageable paging, String subStatusCode, String branchCode, String insCompanyId, String userType);

	Page<QuoteDetails> findByQuoteStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(Pageable paging,
			String subStatusCode, String branchCode, String insCompanyId);

	//List<QuoteDetails> findByEnquiryId(String enquiryId);

	Page<QuoteDetails> findByEnquiryIdOrderByUpdatedDateAsc(Pageable paging, String enquiryId);

	Long countByEnquiryId(String enquiryId);

	Long countByQuoteStatusAndBranchCodeAndInsCompanyIdAndCreatedUsertypeOrderByUpdatedDateDesc(String subStatusCode,
			String branchCode, String insCompanyId, String userType);

	Long countByQuoteStatusAndBranchCodeAndInsCompanyIdAndEnqCreaterUsertypeOrderByUpdatedDateDesc(String subStatusCode,
			String branchCode, String insCompanyId, String userType);

	Long countByQuoteStatusAndBranchCodeAndInsCompanyIdOrderByUpdatedDateDesc(String subStatusCode, String branchCode,
			String insCompanyId);

	Long countByInsCompanyIdAndBranchCode(String insId, String branchCode);

	List<QuoteDetails> findByClientRefNoOrderByUpdatedDateDesc(String clientRefNo);

	List<QuoteDetails> findByEnquiryIdOrderByUpdatedDateDesc(String enquiryId);

	List<QuoteDetails> findByLeadIdOrderByUpdatedDateDesc(String leadId);

	Page<QuoteDetails> findByQuoteStatusAndBranchCodeAndInsCompanyIdAndCreatedByOrderByUpdatedDateDesc(Pageable paging,
			String subStatusCode, String branchCode, String insCompanyId, String createdBy);

	Long countByQuoteStatusAndBranchCodeAndInsCompanyIdAndCreatedByOrderByUpdatedDateDesc(
			String subStatusCode, String branchCode, String insCompanyId, String createdBy);








}
