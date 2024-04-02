package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.LeadDetails;
import com.maan.crm.bean.ProspectPaymentDetails;
import com.maan.crm.bean.ProspectPaymentDetailsId;

@Repository
public interface ProspectRepository extends JpaRepository<ProspectPaymentDetails, ProspectPaymentDetailsId>, JpaSpecificationExecutor<ProspectPaymentDetails>{


	List<ProspectPaymentDetails> findAllByOrderByPaymentDetailsIdDesc();

	List<ProspectPaymentDetails> findByProspectId(int prospectId);

	


}
