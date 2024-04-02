package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.OccupationMaster;
import com.maan.crm.bean.OccupationMasterId;

public interface OccupationMasterRepository extends JpaRepository<OccupationMaster, OccupationMasterId> , JpaSpecificationExecutor<OccupationMaster>{


	// Occupation Drop Down
	List<OccupationMaster> findByStatusOrderByOccupationNameAsc(String status);

	
	// Occupation Get All

	//List<OccupationMaster> findAllOrderByEffectiveDateDesc(String limit, String offset);

	// Occupation Get By Id
	OccupationMaster findByOccupationId(Integer occupationid);

	List<OccupationMaster> findByOccupationIdAndEffectiveDateOrderByAmendIdDesc(Integer occupationId, Date parse);


}
