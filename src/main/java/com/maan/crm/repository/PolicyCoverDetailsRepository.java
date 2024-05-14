package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maan.crm.bean.PolicyCoverDetails;
import com.maan.crm.bean.PolicyCoverDetailsId;

public interface PolicyCoverDetailsRepository extends JpaRepository<PolicyCoverDetails,PolicyCoverDetailsId>{

	List<PolicyCoverDetails> findByPolicyId(String policyId);

}
