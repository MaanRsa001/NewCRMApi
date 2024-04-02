package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.ProspectQuotationInsurerDetails;
import com.maan.crm.bean.ProspectQuotationInsurerDetailsId;

@Repository
public interface ProspectQuotationInsurerDetailsRepository extends JpaRepository<ProspectQuotationInsurerDetails, ProspectQuotationInsurerDetailsId>, JpaSpecificationExecutor<ProspectQuotationInsurerDetails>{

	List<ProspectQuotationInsurerDetails> findAllByOrderByInsurerDetailsIdDesc();

	List<ProspectQuotationInsurerDetails> findByProspectId(int prospectId);



}
