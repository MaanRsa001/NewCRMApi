package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.ProspectQuotationPolicyAccounts;
import com.maan.crm.bean.ProspectQuotationPolicyAccountsId;


public interface ProspectQuotationPolicyAccountsRepository  extends JpaRepository<ProspectQuotationPolicyAccounts, ProspectQuotationPolicyAccountsId>  , JpaSpecificationExecutor<ProspectQuotationPolicyAccounts>{

	List<ProspectQuotationPolicyAccounts> findAllByOrderByPolicyAccIdDesc();

	List<ProspectQuotationPolicyAccounts> findByProspectId(int prospectId);
	
}
