package com.maan.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.StatusMaster;
import com.maan.crm.bean.StatusMasterId;

public interface StatusMasterRepository extends JpaRepository<StatusMaster, StatusMasterId>, JpaSpecificationExecutor<StatusMaster>{


	List<StatusMaster> findByStatusCodeAndSubStatusCodeInOrderByOrderIdAsc(String statusCode, List<String> subStatuses);

	StatusMaster findByStatusCodeAndSubStatusCode(String string, String subStatusCode);
	
}
