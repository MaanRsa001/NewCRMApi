package com.maan.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.maan.crm.bean.StatusMaster;
import com.maan.crm.bean.SubStatusMaster;
import com.maan.crm.bean.SubStatusMasterId;

public interface SubStatusMasterRepository extends JpaRepository<SubStatusMaster, SubStatusMasterId>, JpaSpecificationExecutor<StatusMaster>{

	SubStatusMaster findByStatusCodeAndSubStatusCodeAndUserType(String statusCode, String subStatus, String userType);


	
	
}
