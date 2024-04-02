package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.PolicyTypeMaster;
import com.maan.crm.bean.PolicyTypeMasterId;

public interface PolicyTypeMasterRepository
		extends JpaRepository<PolicyTypeMaster, PolicyTypeMasterId>, JpaSpecificationExecutor<PolicyTypeMaster> {

	List<PolicyTypeMaster> findByPolicytypeIdAndEffectiveDateOrderByAmendIdDesc(Integer policyTypeId, Date parse);

}
