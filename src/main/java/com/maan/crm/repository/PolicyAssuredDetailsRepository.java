package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.PolicyAssuredDetails;
import com.maan.crm.bean.PolicyAssuredDetailsId;



public interface PolicyAssuredDetailsRepository  extends JpaRepository<PolicyAssuredDetails, PolicyAssuredDetailsId>  , JpaSpecificationExecutor<PolicyAssuredDetails>{

	List<PolicyAssuredDetails> findByPolicyId(Integer policyId);

	List<PolicyAssuredDetails> findAllByOrderByAssuredIdDesc();






}
