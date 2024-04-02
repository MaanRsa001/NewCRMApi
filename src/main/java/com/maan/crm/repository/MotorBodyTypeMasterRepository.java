package com.maan.crm.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.maan.crm.bean.MotorBodyTypeMaster;
import com.maan.crm.bean.MotorBodyTypeMasterId;
import com.maan.crm.bean.MotorMakeMaster;
import com.maan.crm.bean.MotorMakeMasterId;
import com.maan.crm.bean.VehicleTypeMaster;
import com.maan.crm.bean.VehicleTypeMasterId;

public interface MotorBodyTypeMasterRepository extends JpaRepository<MotorBodyTypeMaster, MotorBodyTypeMasterId> , JpaSpecificationExecutor<MotorBodyTypeMaster>{

	List<MotorBodyTypeMaster> findByBodyIdAndEffectiveDateOrderByAmendIdDesc(Integer bodyId, Date effectiveDate);



}
