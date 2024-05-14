package com.maan.crm.repository;

import com.maan.crm.bean.PolicyData;
import com.maan.crm.bean.PolicyDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyDataRepository extends JpaRepository<PolicyData, PolicyDataId> {

	PolicyData findByPolicyId(String policyId);
}
