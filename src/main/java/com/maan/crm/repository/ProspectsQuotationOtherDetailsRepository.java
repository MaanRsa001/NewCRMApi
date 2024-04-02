package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ProspectsQuotationOtherDetails;
import com.maan.crm.bean.ProspectsQuotationOtherDetailsId;

public interface ProspectsQuotationOtherDetailsRepository extends JpaRepository<ProspectsQuotationOtherDetails, ProspectsQuotationOtherDetailsId>,JpaSpecificationExecutor<ProspectsQuotationOtherDetails> {

	List<ProspectsQuotationOtherDetails> findAllByOrderByOtherDetailsIdDesc();

	List<ProspectsQuotationOtherDetails> findByProspectId(int prospectId);
	
}
