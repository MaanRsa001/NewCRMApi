package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.AgentMaster;

public interface AgentMasterRepository
		extends JpaRepository<AgentMaster, String>, JpaSpecificationExecutor<AgentMaster> {

	List<AgentMaster> findByBranchCodeAndCompanyIdAndStatus(Pageable paging, String branchCode, String insuranceId,
			String string);

}
