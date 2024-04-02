package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.OccupationMasterId;
import com.maan.crm.bean.VehicleRTOMaster;
import com.maan.crm.bean.VehicleRTOMasterId;

public interface VehicleRTOMasterRepository extends JpaRepository<VehicleRTOMaster, VehicleRTOMasterId> , JpaSpecificationExecutor<VehicleRTOMaster>{

	List<VehicleRTOMaster> findByRtoCodeAndEffectiveDateOrderByAmendIdDesc(String rtoCode, Date parse);


	// Vehicle Drop Down
//	List<VehicleRTOMaster> findByStatusOrderByRtoDescAsc(String status);

	
	// Occupation Get All

	//List<OccupationMaster> findAllOrderByEffectiveDateDesc(String limit, String offset);

}
