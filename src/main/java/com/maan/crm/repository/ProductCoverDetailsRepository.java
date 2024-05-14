package com.maan.crm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maan.crm.bean.ProductCoverDetails;
import com.maan.crm.bean.ProductCoverDetailsId;

public interface ProductCoverDetailsRepository extends JpaRepository<ProductCoverDetails, ProductCoverDetailsId> {

	List<ProductCoverDetails> findByLeadId(String leadId);

	List<ProductCoverDetails> findByLeadIdOrderBySequenceNo(String leadId);


}

