package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ProspectQuotationAddOn;
import com.maan.crm.bean.ProspectQuotationAddOnId;

public interface ProspectQuotationAddOnRepository  extends JpaRepository<ProspectQuotationAddOn,ProspectQuotationAddOnId> , JpaSpecificationExecutor<ProspectQuotationAddOn> {


	List<ProspectQuotationAddOn> findAllByOrderByAddOnidDesc();

	List<ProspectQuotationAddOn> findByProspectId(int prospectId);
	
}
