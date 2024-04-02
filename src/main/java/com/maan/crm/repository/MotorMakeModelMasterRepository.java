package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.MotorMakeMaster;
import com.maan.crm.bean.MotorMakeMasterId;
import com.maan.crm.bean.MotorMakeModelMaster;
import com.maan.crm.bean.MotorMakeModelMasterId;
import com.maan.crm.bean.VehicleTypeMaster;
import com.maan.crm.bean.VehicleTypeMasterId;

public interface MotorMakeModelMasterRepository extends JpaRepository<MotorMakeModelMaster, MotorMakeModelMasterId>,
		JpaSpecificationExecutor<MotorMakeModelMaster> {
	List<MotorMakeModelMaster> findByMakeId(Integer makeId);

	List<MotorMakeModelMaster> findByModelIdAndEffectiveDateOrderByAmendIdDesc(Integer modelId, Date effectiveDate);

}
