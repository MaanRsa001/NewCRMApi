package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.OccupationMasterId;
import com.maan.crm.bean.StateMaster;
import com.maan.crm.bean.StateMasterId;

public interface StateMasterRepository extends JpaRepository<StateMaster, StateMasterId> , JpaSpecificationExecutor<StateMaster>{


	// StateMaster Drop Down
	//List<StateMaster> findByStatusOrderBystateNameAsc(String status);

	// StateMaster Get By Id
	//StateMaster findByStateId(Integer stateId);

	// StateMaster Get All
	List<StateMaster> findByStateIdAndEffectiveDateOrderByAmendIdDesc(Integer stateId, Date parse);


}
