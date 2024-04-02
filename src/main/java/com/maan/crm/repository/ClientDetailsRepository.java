package com.maan.crm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.maan.crm.bean.ClientDetails;
import com.maan.crm.bean.ClientDetailsId;



public interface ClientDetailsRepository  extends JpaRepository<ClientDetails, ClientDetailsId>  , JpaSpecificationExecutor<ClientDetails>{

	ClientDetails findByClientRefNo(String clientRefNo);
//	Page<ClientDetails> findByInsCompanyIdAndBranchCode(Pageable paging, String insId, String branchCode);
	List<ClientDetails> findByClientNameIgnoreCase(String searchValue);
	ClientDetails findByCrno(String searchValue);
	List<ClientDetails> findByClientRefNoIn(List<Integer> clientIds);
//	ClientDetails findByClientRefNoIn(String clientRefNo);
	ClientDetails findByClientRefNoAndCreatedBy(String clientRefNo, String createdBy);
	Page<ClientDetails> findByInsCompanyIdAndBranchCodeAndCreatedByOrderByEntryDateDesc(Pageable paging, String insId,String branchCode, String createdBy);
	
	ClientDetails findByClientRefNoContainingIgnoreCase(String clientRefNo);
	List<ClientDetails> findByClientNameContainingIgnoreCase(String searchValue);
	ClientDetails findByCrnoLikeIgnoreCase(String searchValue);
	Long countByCrno(String crno);
	Long countByMobileNumber(String mobileNumber);
	Long countByCrnoAndClientRefNo(String mobileNumber, String clientRefNo);
	Long countByMobileNumberAndClientRefNo(String mobileNumber, String clientRefNo);
	
	// Pagination with Count
	Page<ClientDetails> findByInsCompanyIdAndBranchCode(Pageable paging, String insId,
			String branchCode);
	Long countByInsCompanyIdAndBranchCode(String insId, String branchCode);
	
	// Client 360 Degree View
	List<ClientDetails> findByClientRefNoOrderByEntryDateDesc(String clientRefNo);
	
	// Campaign Details
	ClientDetails findByMobileNumber(String mobileNumber);
	List<ClientDetails> findByAnnualIncomeIdIn(List<Integer> annualIncomeIds);
	List<ClientDetails> findByOccupationIdIn(List<Integer> occupationIds);
	List<ClientDetails> findByDateOfBirthGreaterThanEqualAndDateOfBirthLessThanEqualOrderByEntryDateDesc(Date startDate,
			Date endDate);
	Page<ClientDetails> findByInsCompanyIdAndBranchCodeOrderByLastVisitedDateDesc(Pageable paging, String insId,
			String branchCode);
	Long countByLastVisitedDateGreaterThanEqualAndLastVisitedDateLessThanEqualAndInsCompanyId(Date startDate, Date endDate, String insId);
	Long countByEntryDateGreaterThanEqualAndEntryDateLessThanEqualAndInsCompanyId(Date startDate, Date endDate,
			String insuranceId);
	
	
/*	@Query( value ="SELECT * FROM crmnew.client_details WHERE ?1 IN (Client_Name ,Client_Ref_No,Mobile_Number, CRNO )" , nativeQuery=true)
	List<ClientDetails> getClientDetails(String searchValue); */
}
