package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.PolicyRiderDetails;
import com.maan.crm.bean.PolicyRiderDetailsId;





public interface PolicyRiderDetailsRepository  extends JpaRepository<PolicyRiderDetails, PolicyRiderDetailsId>  , JpaSpecificationExecutor<PolicyRiderDetails>{

	List<PolicyRiderDetails> findAllByOrderByRiderIdDesc();


	List<PolicyRiderDetails> findByPolicyId(int policyId);



}
