package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.PolicyAccountsDetails;
import com.maan.crm.bean.PolicyAccountsDetailsId;


public interface PolicyAccountsDetailsRepository  extends JpaRepository<PolicyAccountsDetails, PolicyAccountsDetailsId>  , JpaSpecificationExecutor<PolicyAccountsDetails>{

	//Policy Account Details
	List<PolicyAccountsDetails> findByPolicyid(int policyid);

	List<PolicyAccountsDetails> findAllByOrderByPolicyAccIdDesc();

	List<PolicyAccountsDetails> findByPolicyAccIdAndPolicyid(Pageable paging, Integer policyAccId, Integer policyid);




}
